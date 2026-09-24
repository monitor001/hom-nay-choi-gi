package com.gaucon.core.notifications

import android.content.Context
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.LocalTime
import java.time.ZonedDateTime
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

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
        val delay = NextFireCalculator.delayMillis(target, now)
        val request = OneTimeWorkRequestBuilder<DailyActivityWorker>()
            .setInitialDelay(delay, TimeUnit.MILLISECONDS)
            .setInputData(
                workDataOf(
                    DailyActivityWorker.KEY_REMINDER_ID to reminderId,
                    DailyActivityWorker.KEY_CHILD_ID to childId,
                ),
            )
            .addTag(TAG_DAILY)
            .build()
        WorkManager.getInstance(context).enqueueUniqueWork(
            uniqueName(reminderId),
            ExistingWorkPolicy.REPLACE,
            request,
        )
    }

    fun cancel(reminderId: String) {
        WorkManager.getInstance(context).cancelUniqueWork(uniqueName(reminderId))
    }

    companion object {
        const val TAG_DAILY = "daily_activity"
        fun uniqueName(reminderId: String) = "reminder_$reminderId"
    }
}
