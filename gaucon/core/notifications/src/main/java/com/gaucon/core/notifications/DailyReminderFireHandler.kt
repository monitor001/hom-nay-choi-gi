package com.gaucon.core.notifications

import android.content.Context
import com.gaucon.core.datastore.UserPreferences
import com.gaucon.domain.model.ReminderAction
import com.gaucon.domain.model.ReminderLog
import com.gaucon.domain.repository.ActivityLogRepository
import com.gaucon.domain.repository.ChildRepository
import com.gaucon.domain.repository.ReminderRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Đánh giá quy tắc + hiện thông báo + đặt lại báo thức ngày kế.
 * Chạy từ BroadcastReceiver (app đóng) hoặc Worker.
 */
@Singleton
class DailyReminderFireHandler @Inject constructor(
    @ApplicationContext private val context: Context,
    private val prefs: UserPreferences,
    private val reminderRepo: ReminderRepository,
    private val activityLogRepo: ActivityLogRepository,
    private val childRepo: ChildRepository,
    private val scheduler: ReminderScheduler,
    private val notifier: ReminderNotifier,
) {
    suspend fun fire(reminderId: String, childIdHint: String?) {
        val childId = childIdHint
            ?: prefs.activeChildId()
            ?: return

        val reminder = reminderRepo.getById(reminderId)
        val time = reminder?.time ?: LocalTime.of(19, 30)
        if (reminder?.enabled == false) {
            scheduler.cancel(reminderId)
            return
        }

        val now = LocalDateTime.now()
        val todayIso = LocalDate.now().toString()
        val quiet = QuietHours.parse(prefs.quietStart(), prefs.quietEnd())
        val completed = activityLogRepo.completedCountToday(childId, todayIso)
        val shown = reminderRepo.shownSuggestedCountToday(todayIso)
        val maxSuggested = prefs.maxSuggestedPerDay()
        val hasPermission = NotificationChannels.areNotificationsEnabled(context)

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

        scheduler.enqueueDailyActivity(reminderId, childId, time)
    }
}
