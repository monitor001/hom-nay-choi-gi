package com.gaucon.core.notifications

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.work.WorkManager
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.LocalTime
import java.time.ZonedDateTime
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Đặt báo thức hệ thống (setAlarmClock) — vẫn nổ khi app đã đóng / máy Doze.
 * Huỷ WorkManager cũ để tránh nhắc kép.
 */
@Singleton
class ReminderScheduler @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    fun enqueueDailyActivity(
        reminderId: String,
        childId: String,
        time: LocalTime = LocalTime.of(19, 30),
        now: ZonedDateTime = ZonedDateTime.now(),
    ) {
        val target = NextFireCalculator.nextDailyAt(time, now)
        val triggerAt = target.toInstant().toEpochMilli()

        val alarmIntent = Intent(context, ReminderAlarmReceiver::class.java).apply {
            action = ReminderAlarmReceiver.ACTION_FIRE
            putExtra(ReminderAlarmReceiver.EXTRA_REMINDER_ID, reminderId)
            putExtra(ReminderAlarmReceiver.EXTRA_CHILD_ID, childId)
        }
        val alarmPi = PendingIntent.getBroadcast(
            context,
            requestCode(reminderId),
            alarmIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )

        val launch = context.packageManager.getLaunchIntentForPackage(context.packageName)
            ?: Intent()
        val showPi = PendingIntent.getActivity(
            context,
            requestCode(reminderId) + 10_000,
            launch,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )

        val alarmManager = context.getSystemService(AlarmManager::class.java)
        alarmManager.setAlarmClock(
            AlarmManager.AlarmClockInfo(triggerAt, showPi),
            alarmPi,
        )

        // Dọn lịch WorkManager cũ (nếu còn từ bản trước)
        runCatching {
            WorkManager.getInstance(context).cancelUniqueWork(uniqueName(reminderId))
        }
    }

    fun cancel(reminderId: String) {
        val alarmIntent = Intent(context, ReminderAlarmReceiver::class.java).apply {
            action = ReminderAlarmReceiver.ACTION_FIRE
        }
        val alarmPi = PendingIntent.getBroadcast(
            context,
            requestCode(reminderId),
            alarmIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )
        context.getSystemService(AlarmManager::class.java).cancel(alarmPi)
        runCatching {
            WorkManager.getInstance(context).cancelUniqueWork(uniqueName(reminderId))
        }
    }

    companion object {
        const val TAG_DAILY = "daily_activity"
        fun uniqueName(reminderId: String) = "reminder_$reminderId"
        fun requestCode(reminderId: String): Int = reminderId.hashCode() and 0x7FFF_FFFF
    }
}
