package com.gaucon.core.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "gaucon_prefs")

@Singleton
class UserPreferences @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    private object Keys {
        val onboardingDone = booleanPreferencesKey("onboarding_done")
        val activeChildId = stringPreferencesKey("active_child_id")
        val contentVersion = intPreferencesKey("content_version")
        val quietStart = stringPreferencesKey("quiet_start")
        val quietEnd = stringPreferencesKey("quiet_end")
        val maxSuggestedPerDay = intPreferencesKey("max_suggested_per_day")
        val fontScale = floatPreferencesKey("font_scale")
        val notifPermissionAskedAt = longPreferencesKey("notif_permission_asked_at")
        val notifBannerLastShownAt = longPreferencesKey("notif_banner_last_shown_at")
        val allowMeteredContentSync = booleanPreferencesKey("allow_metered_content_sync")
    }

    val onboardingDoneFlow: Flow<Boolean> =
        context.dataStore.data.map { it[Keys.onboardingDone] ?: false }

    suspend fun isOnboardingDone(): Boolean = onboardingDoneFlow.first()

    suspend fun setOnboardingDone(done: Boolean) {
        context.dataStore.edit { it[Keys.onboardingDone] = done }
    }

    suspend fun activeChildId(): String? =
        context.dataStore.data.first()[Keys.activeChildId]

    suspend fun setActiveChildId(id: String) {
        context.dataStore.edit { it[Keys.activeChildId] = id }
    }

    suspend fun contentVersion(): Int =
        context.dataStore.data.first()[Keys.contentVersion] ?: 0

    suspend fun setContentVersion(version: Int) {
        context.dataStore.edit { it[Keys.contentVersion] = version }
    }

    suspend fun quietStart(): String =
        context.dataStore.data.first()[Keys.quietStart] ?: "21:30"

    suspend fun quietEnd(): String =
        context.dataStore.data.first()[Keys.quietEnd] ?: "07:00"

    suspend fun maxSuggestedPerDay(): Int =
        context.dataStore.data.first()[Keys.maxSuggestedPerDay] ?: 2

    suspend fun fontScale(): Float =
        context.dataStore.data.first()[Keys.fontScale] ?: 1.0f

    suspend fun setFontScale(scale: Float) {
        context.dataStore.edit { it[Keys.fontScale] = scale }
    }

    suspend fun notifPermissionAskedAt(): Long =
        context.dataStore.data.first()[Keys.notifPermissionAskedAt] ?: 0L

    suspend fun setNotifPermissionAskedAt(epochMs: Long) {
        context.dataStore.edit { it[Keys.notifPermissionAskedAt] = epochMs }
    }

    suspend fun notifBannerLastShownAt(): Long =
        context.dataStore.data.first()[Keys.notifBannerLastShownAt] ?: 0L

    suspend fun setNotifBannerLastShownAt(epochMs: Long) {
        context.dataStore.edit { it[Keys.notifBannerLastShownAt] = epochMs }
    }
}
