package com.gaucon.feature.reminder

import com.gaucon.core.notifications.ReminderScheduler
import com.gaucon.domain.model.Reminder
import com.gaucon.domain.model.ReminderType
import com.gaucon.domain.repository.ReminderRepository
import java.time.LocalTime
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Partial reminder feature — schedules DAILY_ACTIVITY 19:30 (S03 full UX deferred).
 */
@Singleton
class DailyActivityReminderFacade @Inject constructor(
    private val reminderRepository: ReminderRepository,
    private val scheduler: ReminderScheduler,
) {
    suspend fun ensureDefaultEnabled(childId: String) {
        val id = DEFAULT_ID
        val existing = reminderRepository.getById(id)
        if (existing == null) {
            reminderRepository.upsert(
                Reminder(
                    id = id,
                    childId = childId,
                    type = ReminderType.DAILY_ACTIVITY,
                    enabled = true,
                    time = LocalTime.of(19, 30),
                    isUserDefined = false,
                ),
            )
        }
        scheduler.enqueueDailyActivity(id, childId, LocalTime.of(19, 30))
    }

    companion object {
        const val DEFAULT_ID = "daily_activity_default"
    }
}
