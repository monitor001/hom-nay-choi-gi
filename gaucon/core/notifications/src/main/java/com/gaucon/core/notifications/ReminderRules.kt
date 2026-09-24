package com.gaucon.core.notifications

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime

/**
 * Next fire for inexact WorkManager delay — no exact alarm.
 */
object NextFireCalculator {

    fun nextDailyAt(
        time: LocalTime,
        now: ZonedDateTime = ZonedDateTime.now(),
    ): ZonedDateTime {
        var candidate = now.toLocalDate().atTime(time).atZone(now.zone)
        if (!candidate.isAfter(now)) {
            candidate = candidate.plusDays(1)
        }
        return candidate
    }

    fun delayMillis(target: ZonedDateTime, now: ZonedDateTime = ZonedDateTime.now()): Long =
        (target.toInstant().toEpochMilli() - now.toInstant().toEpochMilli()).coerceAtLeast(0L)
}

data class QuietHours(val start: LocalTime, val end: LocalTime) {
    /** Inclusive start, exclusive end; supports overnight windows. */
    fun contains(time: LocalTime): Boolean {
        return if (start <= end) {
            time >= start && time < end
        } else {
            time >= start || time < end
        }
    }

    companion object {
        fun parse(startHm: String, endHm: String): QuietHours =
            QuietHours(LocalTime.parse(startHm), LocalTime.parse(endHm))
    }
}

enum class ReminderEvalResult {
    SHOW,
    SKIP_NO_PERMISSION,
    SKIP_QUIET_HOURS,
    SKIP_ALREADY_DONE,
    SKIP_MAX_SUGGESTED,
}

/**
 * R1–R4 evaluation order: permission → R1 → R4 → R2 → SHOW.
 */
object ReminderRules {

    fun evaluate(
        hasNotificationPermission: Boolean,
        now: LocalDateTime,
        quietHours: QuietHours,
        isUserDefined: Boolean,
        completedCountToday: Int,
        shownSuggestedToday: Int,
        maxSuggestedPerDay: Int,
    ): ReminderEvalResult {
        if (!hasNotificationPermission) return ReminderEvalResult.SKIP_NO_PERMISSION
        if (!isUserDefined && quietHours.contains(now.toLocalTime())) {
            return ReminderEvalResult.SKIP_QUIET_HOURS
        }
        if (completedCountToday >= 1) return ReminderEvalResult.SKIP_ALREADY_DONE
        if (shownSuggestedToday >= maxSuggestedPerDay) return ReminderEvalResult.SKIP_MAX_SUGGESTED
        return ReminderEvalResult.SHOW
    }
}

/** Soft-prompt stub for R3 (consecutive ignored). */
data class AutoDegradeState(val consecutiveIgnored: Int = 0) {
    fun shouldSoftPrompt(): Boolean = consecutiveIgnored >= 3
    fun onShownWithoutOpen(): AutoDegradeState = copy(consecutiveIgnored = consecutiveIgnored + 1)
    fun onOpenedOrDone(): AutoDegradeState = copy(consecutiveIgnored = 0)
}

fun dayBoundsMs(date: LocalDate, zone: ZoneId = ZoneId.systemDefault()): Pair<Long, Long> {
    val start = date.atStartOfDay(zone).toInstant().toEpochMilli()
    val end = date.plusDays(1).atStartOfDay(zone).toInstant().toEpochMilli()
    return start to end
}
