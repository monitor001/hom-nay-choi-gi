package com.gaucon.core.notifications

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

/** Giữ tương thích; lịch chính dùng AlarmManager. */
@HiltWorker
class DailyActivityWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val fireHandler: DailyReminderFireHandler,
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val reminderId = inputData.getString(KEY_REMINDER_ID) ?: DEFAULT_REMINDER_ID
        val childId = inputData.getString(KEY_CHILD_ID)
        fireHandler.fire(reminderId, childId)
        return Result.success()
    }

    companion object {
        const val KEY_REMINDER_ID = "reminder_id"
        const val KEY_CHILD_ID = "child_id"
        const val DEFAULT_REMINDER_ID = "daily_activity_default"
    }
}
