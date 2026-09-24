package com.gaucon.domain.model

import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime

data class Child(
    val id: String,
    val displayName: String,
    val birthDate: LocalDate,
    val gender: String? = null,
    val photoUri: String? = null,
)

data class Activity(
    val id: String,
    val ageMinMonths: Int,
    val ageMaxMonths: Int,
    val domains: List<Domain>,
    val title: String,
    val goal: String,
    val materials: List<String>,
    val steps: List<String>,
    val safety: String?,
    val easier: String? = null,
    val harder: String? = null,
    val durationMinutes: Int,
    val isRetired: Boolean = false,
    val contentVersion: Int = 1,
    val reviewedBy: List<String> = emptyList(),
    val contentStatus: ContentStatus = ContentStatus.DRAFT_UNREVIEWED,
    val parentPhrases: List<String> = emptyList(),
)

data class ActivityLog(
    val id: String,
    val childId: String,
    val activityId: String,
    val completedAt: Instant,
    val feedback: Feedback? = null,
)

data class DailyPick(
    val childId: String,
    val dateIso: String,
    val activityIds: List<String>,
    val generatedAt: Instant,
)

data class Reminder(
    val id: String,
    val childId: String,
    val type: ReminderType,
    val enabled: Boolean,
    val time: LocalTime,
    val isUserDefined: Boolean,
)

data class ReminderLog(
    val id: String,
    val reminderId: String,
    val firedAt: Instant,
    val action: ReminderAction,
)
