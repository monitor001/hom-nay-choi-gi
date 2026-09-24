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

data class MilestoneStub(
    val id: String,
    val label: String,
    val band: String,
)

data class MilestoneObservation(
    val childId: String,
    val milestoneId: String,
    val status: MilestoneObsStatus,
    val updatedAt: Instant,
)

data class JournalEntry(
    val id: String,
    val childId: String,
    val createdAt: Instant,
    val text: String,
    val linkedActivityId: String? = null,
)

data class GxLedgerEntry(
    val id: String,
    val childId: String,
    val createdAt: Instant,
    val amount: Int,
    val kind: String,
    val refId: String? = null,
    val note: String? = null,
)

object GxKinds {
    const val COMPLETE = "COMPLETE"
    const val FEEDBACK = "FEEDBACK"
    const val JOURNAL = "JOURNAL"
    const val STREAK = "STREAK"
    const val STREAK_7 = "STREAK_7"
    const val WEEK_KPI = "WEEK_KPI"
    const val REDEEM = "REDEEM"
}

object GxRates {
    const val COMPLETE = 15
    const val FEEDBACK = 3
    const val JOURNAL = 5
    const val STREAK_DAY = 4
    const val STREAK_7 = 15
    const val WEEK_KPI = 25
    const val DAILY_CAP = 45
    const val WEEKLY_CAP = 150
    const val MAX_COMPLETE_EARNS_PER_DAY = 3
    const val COOLDOWN_MS = 8L * 60 * 1000
}
