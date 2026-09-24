package com.gaucon.contentseed

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SeedFile(
    val version: Int = 1,
    @SerialName("content_status") val contentStatus: String = "draft_unreviewed",
    val activities: List<SeedActivityDto> = emptyList(),
)

@Serializable
data class SeedActivityDto(
    val id: String,
    val title: String,
    val ageMinMonths: Int,
    val ageMaxMonths: Int,
    val domains: List<String> = emptyList(),
    val durationMinutes: Int = 10,
    val materials: List<String> = emptyList(),
    val goal: String = "",
    val steps: List<String> = emptyList(),
    val parentPhrases: List<String> = emptyList(),
    val easier: String? = null,
    val harder: String? = null,
    val safety: String? = null,
    @SerialName("reviewed_by") val reviewedBy: List<String> = emptyList(),
    val isPremium: Boolean = false,
)
