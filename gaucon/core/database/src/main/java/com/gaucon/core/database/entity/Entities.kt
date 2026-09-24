package com.gaucon.core.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "child")
data class ChildEntity(
    @PrimaryKey val id: String,
    val displayName: String,
    val birthDate: String,
    val gender: String? = null,
    val photoUri: String? = null,
)

@Entity(tableName = "activity", indices = [Index("isRetired")])
data class ActivityEntity(
    @PrimaryKey val id: String,
    val ageMinMonths: Int,
    val ageMaxMonths: Int,
    val domainsJson: String,
    val title: String,
    val goal: String,
    val materialsJson: String,
    val stepsJson: String,
    val safety: String?,
    val easier: String?,
    val harder: String?,
    val durationMinutes: Int,
    val isRetired: Boolean,
    val contentVersion: Int,
    val reviewedByJson: String,
    val contentStatus: String,
    val parentPhrasesJson: String,
)

@Entity(
    tableName = "activity_log",
    indices = [Index("childId"), Index("completedAt")],
)
data class ActivityLogEntity(
    @PrimaryKey val id: String,
    val childId: String,
    val activityId: String,
    val completedAt: Long,
    val feedback: String?,
)

@Entity(
    tableName = "daily_pick",
    primaryKeys = ["childId", "dateIso"],
    indices = [Index("childId")],
)
data class DailyPickEntity(
    val childId: String,
    val dateIso: String,
    val activityIdsJson: String,
    val generatedAt: Long,
)

@Entity(tableName = "reminder", indices = [Index("childId")])
data class ReminderEntity(
    @PrimaryKey val id: String,
    val childId: String,
    val type: String,
    val enabled: Boolean,
    val time: String,
    val isUserDefined: Boolean,
)

@Entity(tableName = "reminder_log", indices = [Index("reminderId"), Index("firedAt")])
data class ReminderLogEntity(
    @PrimaryKey val id: String,
    val reminderId: String,
    val firedAt: Long,
    val action: String,
)

@Entity(
    tableName = "milestone_status",
    primaryKeys = ["childId", "milestoneId"],
)
data class MilestoneStatusEntity(
    val childId: String,
    val milestoneId: String,
    val status: String,
    val updatedAt: Long,
)

@Entity(tableName = "journal_entry", indices = [Index("childId"), Index("createdAt")])
data class JournalEntryEntity(
    @PrimaryKey val id: String,
    val childId: String,
    val createdAt: Long,
    val text: String,
    val linkedActivityId: String? = null,
)

@Entity(
    tableName = "gx_ledger",
    indices = [Index("childId"), Index("createdAt"), Index(value = ["childId", "kind", "refId"])],
)
data class GxLedgerEntity(
    @PrimaryKey val id: String,
    val childId: String,
    val createdAt: Long,
    val amount: Int,
    val kind: String,
    val refId: String? = null,
    val note: String? = null,
)
