package com.gaucon.core.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationManagerCompat

object NotificationChannels {
    const val ACTIVITY = "ch_activity"
    const val ROUTINE = "ch_routine"
    const val HEALTH = "ch_health"
    const val GROWTH = "ch_growth"

    fun ensureCreated(context: Context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return
        val manager = context.getSystemService(NotificationManager::class.java) ?: return
        val channels = listOf(
            NotificationChannel(ACTIVITY, "Gợi ý hoạt động", NotificationManager.IMPORTANCE_DEFAULT),
            NotificationChannel(ROUTINE, "Nếp sinh hoạt", NotificationManager.IMPORTANCE_DEFAULT),
            NotificationChannel(HEALTH, "Sức khỏe & tiêm chủng", NotificationManager.IMPORTANCE_HIGH),
            NotificationChannel(GROWTH, "Mốc & nhật ký", NotificationManager.IMPORTANCE_LOW),
        )
        manager.createNotificationChannels(channels)
    }

    fun areNotificationsEnabled(context: Context): Boolean =
        NotificationManagerCompat.from(context).areNotificationsEnabled()
}
