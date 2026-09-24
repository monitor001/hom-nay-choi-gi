package com.gaucon.core.notifications

import com.gaucon.domain.repository.ReminderRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReminderRescheduler @Inject constructor(
    private val reminderRepository: ReminderRepository,
    private val scheduler: ReminderScheduler,
) {
    suspend fun rescheduleAllEnabled() {
        reminderRepository.getEnabled().forEach { reminder ->
            scheduler.enqueueDailyActivity(reminder.id, reminder.childId, reminder.time)
        }
    }
}
