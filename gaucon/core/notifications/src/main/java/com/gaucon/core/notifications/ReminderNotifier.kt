package com.gaucon.core.notifications

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReminderNotifier @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    fun showDailyActivity(childDisplayName: String?) {
        val title = "Hôm nay mình thử một hoạt động ngắn?"
        val body = if (childDisplayName.isNullOrBlank()) {
            "Mỗi bé một nhịp riêng — mở Hôm nay chơi gì? khi bạn sẵn sàng."
        } else {
            "Chào bạn — gợi ý cho $childDisplayName đang chờ."
        }
        val launch = context.packageManager.getLaunchIntentForPackage(context.packageName)
            ?: Intent()
        val pending = PendingIntent.getActivity(
            context,
            0,
            launch,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )
        val notification = NotificationCompat.Builder(context, NotificationChannels.ACTIVITY)
            .setSmallIcon(android.R.drawable.ic_menu_my_calendar)
            .setContentTitle(title)
            .setContentText(body)
            .setContentIntent(pending)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
        NotificationManagerCompat.from(context).notify(NOTIF_ID_DAILY, notification)
    }

    companion object {
        const val NOTIF_ID_DAILY = 1901
    }
}
