package com.gaucon.contentseed

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResourceCatalogLoader @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    private val json = Json { ignoreUnknownKeys = true }
    private val mutex = Mutex()
    private var cached: Cached? = null

    suspend fun catalog(): Cached = mutex.withLock {
        cached ?: load().also { cached = it }
    }

    suspend fun getById(id: String): ResourceItem? =
        catalog().itemsById[id]

    suspend fun linkedForActivity(activityId: String): List<ResourceItem> {
        val c = catalog()
        val ids = c.links[activityId].orEmpty()
        return ids.mapNotNull { c.itemsById[it] }
    }

    suspend fun items(categoryId: String? = null): List<ResourceItem> {
        val c = catalog()
        return if (categoryId.isNullOrBlank() || categoryId == "ALL") {
            c.items
        } else {
            c.items.filter { it.category == categoryId }
        }
    }

    private suspend fun load(): Cached = withContext(Dispatchers.IO) {
        val catalogText = context.assets.open(CATALOG_ASSET).bufferedReader().use { it.readText() }
        val linksText = context.assets.open(LINKS_ASSET).bufferedReader().use { it.readText() }
        val catalog = json.decodeFromString<ResourceCatalogFile>(catalogText)
        val links = json.decodeFromString<ResourceLinksFile>(linksText)
        val labelByCat = catalog.categories.associate { it.id to it.label }
        val items = catalog.items.map { dto ->
            ResourceItem(
                id = dto.id,
                category = dto.category,
                categoryLabel = labelByCat[dto.category] ?: dto.category,
                title = dto.title,
                ageHint = dto.ageHint,
                source = dto.source,
                license = dto.license,
                howToUse = dto.howToUse,
                body = dto.body,
                region = dto.region,
                imageAssetPath = dto.image?.let { "resources/$it" },
                storyPrompt = dto.storyPrompt,
            )
        }
        Cached(
            version = catalog.version,
            disclaimer = catalog.disclaimer,
            categories = catalog.categories,
            items = items,
            itemsById = items.associateBy { it.id },
            links = links.links,
        )
    }

    data class Cached(
        val version: Int,
        val disclaimer: String,
        val categories: List<ResourceCategoryDto>,
        val items: List<ResourceItem>,
        val itemsById: Map<String, ResourceItem>,
        val links: Map<String, List<String>>,
    )

    companion object {
        const val CATALOG_ASSET = "resources_catalog.json"
        const val LINKS_ASSET = "resources_links.json"
    }
}
