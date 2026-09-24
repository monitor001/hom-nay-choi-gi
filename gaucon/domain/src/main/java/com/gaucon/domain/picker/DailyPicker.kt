package com.gaucon.domain.picker

import com.gaucon.domain.model.Activity
import com.gaucon.domain.model.Domain
import com.gaucon.domain.model.Feedback
import java.time.LocalDate
import kotlin.random.Random

/**
 * Daily activity picker — CONSENSUS §3.1 / TECH_ARCHITECTURE §3.1.
 * Stable for the same childId|date seed; does not persist (caller persists DailyPick).
 */
class DailyPicker(
    private val weightDomainRecency: Double = 3.0,
    private val weightPracticing: Double = 2.0,
    private val weightNotFitPenalty: Double = 4.0,
) {

    data class HistoryItem(
        val activityId: String,
        val primaryDomain: Domain?,
        val completedDate: LocalDate,
        val feedback: Feedback? = null,
    )

    data class Input(
        val childId: String,
        val ageMonths: Int,
        val today: LocalDate,
        val activities: List<Activity>,
        val history14Days: List<HistoryItem> = emptyList(),
        val practicingDomains: Set<Domain> = emptySet(),
        val maxItems: Int = 3,
        val maxTotalMinutes: Int = 30,
    )

    fun pick(input: Input): List<String> {
        val excludeIds = input.history14Days
            .filter { !it.completedDate.isBefore(input.today.minusDays(14)) }
            .map { it.activityId }
            .toSet()

        val notFitSimilar = input.history14Days
            .filter { it.feedback == Feedback.NOT_FIT }
            .mapNotNull { it.primaryDomain }
            .toSet()

        val domainLastUsed = mutableMapOf<Domain, LocalDate>()
        input.history14Days.forEach { item ->
            val d = item.primaryDomain ?: return@forEach
            val prev = domainLastUsed[d]
            if (prev == null || item.completedDate.isAfter(prev)) {
                domainLastUsed[d] = item.completedDate
            }
        }

        val candidates = input.activities.filter { a ->
            !a.isRetired &&
                input.ageMonths in a.ageMinMonths..a.ageMaxMonths &&
                a.id !in excludeIds
        }

        if (candidates.isEmpty()) return emptyList()

        val seed = dailySeed(input.childId, input.today)
        val rng = Random(seed)

        data class Scored(val activity: Activity, val score: Double, val tie: Int)

        val scored = candidates.map { a ->
            val primary = a.domains.firstOrNull()
            var score = 0.0
            if (primary != null) {
                val last = domainLastUsed[primary]
                val daysSince = if (last == null) 14 else {
                    java.time.temporal.ChronoUnit.DAYS.between(last, input.today).toInt().coerceIn(0, 14)
                }
                score += weightDomainRecency * daysSince
                if (primary in input.practicingDomains) score += weightPracticing
                if (primary in notFitSimilar) score -= weightNotFitPenalty
            }
            Scored(a, score, rng.nextInt())
        }.sortedWith(
            compareByDescending<Scored> { it.score }.thenBy { it.tie },
        )

        val picked = mutableListOf<Activity>()
        var totalMin = 0
        val domainsUsed = mutableSetOf<Domain>()

        for (item in scored) {
            if (picked.size >= input.maxItems) break
            val a = item.activity
            if (totalMin + a.durationMinutes > input.maxTotalMinutes && picked.isNotEmpty()) continue
            picked += a
            totalMin += a.durationMinutes
            a.domains.firstOrNull()?.let { domainsUsed += it }
        }

        // Prefer ≥2 domains when enough candidates remain
        if (picked.size >= 2 && domainsUsed.size < 2) {
            val alt = scored.map { it.activity }.firstOrNull { cand ->
                cand.id !in picked.map { it.id } &&
                    cand.domains.firstOrNull() !in domainsUsed &&
                    totalMin - (picked.lastOrNull()?.durationMinutes ?: 0) + cand.durationMinutes <= input.maxTotalMinutes
            }
            if (alt != null && picked.isNotEmpty()) {
                picked[picked.lastIndex] = alt
            }
        }

        return picked.map { it.id }
    }

    companion object {
        fun dailySeed(childId: String, day: LocalDate): Long =
            (childId + "|" + day).hashCode().toLong()
    }
}
