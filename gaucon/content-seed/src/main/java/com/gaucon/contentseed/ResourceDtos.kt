package com.gaucon.contentseed

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResourceCatalogFile(
    val version: Int = 1,
    @SerialName("content_status") val contentStatus: String = "draft_unreviewed",
    val disclaimer: String = "",
    val categories: List<ResourceCategoryDto> = emptyList(),
    val items: List<ResourceItemDto> = emptyList(),
)

@Serializable
data class ResourceCategoryDto(
    val id: String,
    val label: String,
)

@Serializable
data class ResourceItemDto(
    val id: String,
    val category: String,
    val title: String,
    val ageHint: String? = null,
    val source: String? = null,
    val license: String? = null,
    val howToUse: String? = null,
    val body: String = "",
    val region: String? = null,
    val themes: List<String> = emptyList(),
    val image: String? = null,
    val storyPrompt: String? = null,
)

@Serializable
data class ResourceLinksFile(
    val version: Int = 1,
    val note: String? = null,
    val links: Map<String, List<String>> = emptyMap(),
)

data class ResourceItem(
    val id: String,
    val category: String,
    val categoryLabel: String,
    val title: String,
    val ageHint: String?,
    val source: String?,
    val license: String?,
    val howToUse: String?,
    val body: String,
    val region: String?,
    /** Asset-relative path under `resources/`, e.g. `images/chuoi.png` */
    val imageAssetPath: String?,
    val storyPrompt: String?,
)
