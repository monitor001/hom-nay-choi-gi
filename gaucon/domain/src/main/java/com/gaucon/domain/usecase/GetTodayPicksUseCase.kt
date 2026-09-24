package com.gaucon.domain.usecase

import com.gaucon.domain.ageMonths
import com.gaucon.domain.model.Activity
import com.gaucon.domain.model.DailyPick
import com.gaucon.domain.picker.DailyPicker
import com.gaucon.domain.repository.ActivityLogRepository
import com.gaucon.domain.repository.ActivityRepository
import com.gaucon.domain.repository.ChildRepository
import com.gaucon.domain.repository.DailyPickRepository
import java.time.Clock
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

class GetTodayPicksUseCase(
    private val childRepo: ChildRepository,
    private val activityRepo: ActivityRepository,
    private val pickRepo: DailyPickRepository,
    private val logRepo: ActivityLogRepository,
    private val picker: DailyPicker = DailyPicker(),
    private val clock: Clock = Clock.systemDefaultZone(),
) {
    suspend operator fun invoke(
        excludeAlreadyShownIds: Set<String> = emptySet(),
    ): List<Activity> {
        val child = childRepo.getActive() ?: return emptyList()
        val today = LocalDate.now(clock)
        val dateIso = today.toString()
        val existing = pickRepo.get(child.id, dateIso)
        if (existing != null && existing.activityIds.isNotEmpty()) {
            var ids = existing.activityIds
            if (excludeAlreadyShownIds.isNotEmpty()) {
                // Refresh: avoid ids already shown today when more candidates exist
                val refreshed = pickFresh(child.id, child.birthDate, today, excludeAlreadyShownIds)
                if (refreshed.isNotEmpty()) {
                    ids = refreshed
                    pickRepo.upsert(
                        DailyPick(child.id, dateIso, ids, Instant.now(clock)),
                    )
                }
            }
            return activityRepo.getByIds(ids)
        }
        val ids = pickFresh(child.id, child.birthDate, today, emptySet())
        pickRepo.upsert(DailyPick(child.id, dateIso, ids, Instant.now(clock)))
        return activityRepo.getByIds(ids)
    }

    private suspend fun pickFresh(
        childId: String,
        birthDate: LocalDate,
        today: LocalDate,
        excludeShown: Set<String>,
    ): List<String> {
        val age = ageMonths(birthDate, today)
        val activities = activityRepo.getAllActive()
            .filter { it.id !in excludeShown }
        val since = today.minusDays(14)
            .atStartOfDay(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli()
        val logs = logRepo.recentForChild(childId, since)
        val activityById = activities.associateBy { it.id }
        val history = logs.map { log ->
            DailyPicker.HistoryItem(
                activityId = log.activityId,
                primaryDomain = activityById[log.activityId]?.domains?.firstOrNull(),
                completedDate = log.completedAt.atZone(ZoneId.systemDefault()).toLocalDate(),
                feedback = log.feedback,
            )
        }
        return picker.pick(
            DailyPicker.Input(
                childId = childId,
                ageMonths = age,
                today = today,
                activities = activities,
                history14Days = history,
            ),
        )
    }
}
