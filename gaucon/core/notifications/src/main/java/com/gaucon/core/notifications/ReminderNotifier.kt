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
        post(title, body, NOTIF_ID_DAILY)
    }

    /** Kiểm tra nhanh quyền + kênh (không ghi sổ nhắc). */
    fun showTestPing() {
        post(
            title = "Thử thông báo thành công",
            body = "Khi đến giờ nhắc, tin nhắn tương tự sẽ hiện dù app đang đóng.",
            id = NOTIF_ID_TEST,
        )
    }

    private fun post(title: String, body: String, id: Int) {
        if (!NotificationChannels.areNotificationsEnabled(context)) return
        val launch = context.packageManager.getLaunchIntentForPackage(context.packageName)
            ?: Intent()
        launch.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pending = PendingIntent.getActivity(
            context,
            id,
            launch,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )
        val notification = NotificationCompat.Builder(context, NotificationChannels.ACTIVITY)
            .setSmallIcon(android.R.drawable.ic_menu_my_calendar)
            .setContentTitle(title)
            .setContentText(body)
            .setStyle(NotificationCompat.BigTextStyle().bigText(body))
            .setContentIntent(pending)
            .setAutoCancel(true)
            .setCategory(NotificationCompat.CATEGORY_REMINDER)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .build()
        NotificationManagerCompat.from(context).notify(id, notification)
    }

    companion object {
        const val NOTIF_ID_DAILY = 1901
        const val NOTIF_ID_TEST = 1902
    }
}
