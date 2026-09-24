package com.gaucon.core.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

/** Nhận báo thức khi app có thể đang đóng hoàn toàn. */
@AndroidEntryPoint
class ReminderAlarmReceiver : BroadcastReceiver() {

    @Inject lateinit var fireHandler: DailyReminderFireHandler

    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action != ACTION_FIRE) return
        val reminderId = intent.getStringExtra(EXTRA_REMINDER_ID) ?: DailyActivityWorker.DEFAULT_REMINDER_ID
        val childId = intent.getStringExtra(EXTRA_CHILD_ID)
        val pending = goAsync()
        val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
        scope.launch {
            try {
                fireHandler.fire(reminderId, childId)
            } finally {
                pending.finish()
            }
        }
    }

    companion object {
        const val ACTION_FIRE = "com.gaucon.core.notifications.ACTION_DAILY_REMINDER"
        const val EXTRA_REMINDER_ID = "reminder_id"
        const val EXTRA_CHILD_ID = "child_id"
    }
}
