package com.gaucon.core.notifications

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.gaucon.core.datastore.UserPreferences
import com.gaucon.domain.model.ReminderAction
import com.gaucon.domain.model.ReminderLog
import com.gaucon.domain.repository.ActivityLogRepository
import com.gaucon.domain.repository.ChildRepository
import com.gaucon.domain.repository.ReminderRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.UUID

@HiltWorker
class DailyActivityWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val prefs: UserPreferences,
    private val reminderRepo: ReminderRepository,
    private val activityLogRepo: ActivityLogRepository,
    private val childRepo: ChildRepository,
    private val scheduler: ReminderScheduler,
    private val notifier: ReminderNotifier,
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val reminderId = inputData.getString(KEY_REMINDER_ID) ?: DEFAULT_REMINDER_ID
        val childId = inputData.getString(KEY_CHILD_ID)
            ?: prefs.activeChildId()
            ?: return rescheduleAndSuccess(reminderId, null)

        val reminder = reminderRepo.getById(reminderId)
        val time = reminder?.time ?: LocalTime.of(19, 30)
        val enabled = reminder?.enabled != false

        if (!enabled) {
            return Result.success()
        }

        val now = LocalDateTime.now()
        val todayIso = LocalDate.now().toString()
        val quiet = QuietHours.parse(prefs.quietStart(), prefs.quietEnd())
        val completed = activityLogRepo.completedCountToday(childId, todayIso)
        val shown = reminderRepo.shownSuggestedCountToday(todayIso)
        val maxSuggested = prefs.maxSuggestedPerDay()
        val hasPermission = NotificationChannels.areNotificationsEnabled(applicationContext)

        val eval = ReminderRules.evaluate(
            hasNotificationPermission = hasPermission,
            now = now,
            quietHours = quiet,
            isUserDefined = reminder?.isUserDefined == true,
            completedCountToday = completed,
            shownSuggestedToday = shown,
            maxSuggestedPerDay = maxSuggested,
        )

        when (eval) {
            ReminderEvalResult.SHOW -> {
                val child = childRepo.getById(childId)
                notifier.showDailyActivity(child?.displayName)
                reminderRepo.insertLog(
                    ReminderLog(
                        id = UUID.randomUUID().toString(),
                        reminderId = reminderId,
                        firedAt = Instant.now(),
                        action = ReminderAction.SHOWN,
                    ),
                )
            }
            else -> {
                reminderRepo.insertLog(
                    ReminderLog(
                        id = UUID.randomUUID().toString(),
                        reminderId = reminderId,
                        firedAt = Instant.now(),
                        action = ReminderAction.SKIPPED_RULE,
                    ),
                )
            }
        }

        return rescheduleAndSuccess(reminderId, childId, time)
    }

    private fun rescheduleAndSuccess(
        reminderId: String,
        childId: String?,
        time: LocalTime = LocalTime.of(19, 30),
    ): Result {
        if (childId != null) {
            scheduler.enqueueDailyActivity(reminderId, childId, time)
        }
        return Result.success()
    }

    companion object {
        const val KEY_REMINDER_ID = "reminder_id"
        const val KEY_CHILD_ID = "child_id"
        const val DEFAULT_REMINDER_ID = "daily_activity_default"
    }
}
