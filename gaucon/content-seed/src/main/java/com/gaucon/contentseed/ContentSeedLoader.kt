package com.gaucon.contentseed

import android.content.Context
import com.gaucon.core.datastore.UserPreferences
import com.gaucon.domain.model.Activity
import com.gaucon.domain.model.ContentStatus
import com.gaucon.domain.model.Domain
import com.gaucon.domain.repository.ActivityRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContentSeedLoader @Inject constructor(
    @ApplicationContext private val context: Context,
    private val activityRepository: ActivityRepository,
    private val prefs: UserPreferences,
) {
    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    fun readVersion(): Int = readSeed().version

    fun readActivities(): List<Activity> {
        val seed = readSeed()
        return seed.activities.map { it.toDomain(seed.version) }
    }

    suspend fun upsertIfNewer() {
        val seed = readSeed()
        if (seed.activities.isEmpty()) {
            error("content_seed.json has 0 activities — refusing to upsert")
        }
        val local = prefs.contentVersion()
        val existing = activityRepository.getAllActive()
        if (seed.version > local || existing.isEmpty()) {
            activityRepository.upsertAll(seed.activities.map { it.toDomain(seed.version) })
            prefs.setContentVersion(seed.version)
        }
    }

    private fun readSeed(): SeedFile {
        context.assets.open(ASSET_NAME).bufferedReader().use { reader ->
            return json.decodeFromString(SeedFile.serializer(), reader.readText())
        }
    }

    companion object {
        const val ASSET_NAME = "content_seed.json"
    }
}

internal fun SeedActivityDto.toDomain(contentVersion: Int): Activity = Activity(
    id = id,
    ageMinMonths = ageMinMonths,
    ageMaxMonths = ageMaxMonths,
    domains = domains.mapNotNull { raw ->
        runCatching { Domain.valueOf(raw.trim()) }.getOrNull()
    },
    title = title,
    goal = goal,
    materials = materials,
    steps = steps,
    safety = safety,
    easier = easier,
    harder = harder,
    durationMinutes = durationMinutes,
    isRetired = false,
    contentVersion = contentVersion,
    reviewedBy = reviewedBy,
    contentStatus = ContentStatus.DRAFT_UNREVIEWED,
    parentPhrases = parentPhrases,
)
