package com.gaucon.app

import android.app.Application
import android.util.Log
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.gaucon.contentseed.ContentSeedLoader
import com.gaucon.core.notifications.NotificationChannels
import com.gaucon.core.notifications.ReminderRescheduler
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class GauConApp : Application(), Configuration.Provider {

    @Inject lateinit var workerFactory: HiltWorkerFactory
    @Inject lateinit var contentSeedLoader: ContentSeedLoader
    @Inject lateinit var reminderRescheduler: ReminderRescheduler

    private val appScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onCreate() {
        super.onCreate()
        NotificationChannels.ensureCreated(this)
        appScope.launch {
            runCatching { contentSeedLoader.upsertIfNewer() }
                .onFailure { Log.e(TAG, "Content seed failed", it) }
            runCatching { reminderRescheduler.rescheduleAllEnabled() }
                .onFailure { Log.e(TAG, "Reminder reschedule failed", it) }
        }
    }

    override val workManagerConfiguration: Configuration
        get() {
            check(::workerFactory.isInitialized) {
                "HiltWorkerFactory not ready — ensure WorkManager default initializer is disabled"
            }
            return Configuration.Builder()
                .setWorkerFactory(workerFactory)
                .build()
        }

    companion object {
        private const val TAG = "GauConApp"
    }
}
