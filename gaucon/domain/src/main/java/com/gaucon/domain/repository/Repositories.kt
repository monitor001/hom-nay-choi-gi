package com.gaucon.domain.repository

import com.gaucon.domain.model.Activity
import com.gaucon.domain.model.ActivityLog
import com.gaucon.domain.model.Child
import com.gaucon.domain.model.DailyPick
import com.gaucon.domain.model.GxLedgerEntry
import com.gaucon.domain.model.JournalEntry
import com.gaucon.domain.model.MilestoneObsStatus
import com.gaucon.domain.model.Reminder
import com.gaucon.domain.model.ReminderLog

interface ChildRepository {
    suspend fun upsert(child: Child)
    suspend fun getById(id: String): Child?
    suspend fun getActive(): Child?
}

interface ActivityRepository {
    suspend fun upsertAll(activities: List<Activity>)
    suspend fun getAllActive(): List<Activity>
    suspend fun getById(id: String): Activity?
    suspend fun getByIds(ids: List<String>): List<Activity>
}

interface ActivityLogRepository {
    suspend fun insert(log: ActivityLog)
    suspend fun completedCountToday(childId: String, dateIso: String): Int
    suspend fun recentForChild(childId: String, sinceEpochMs: Long): List<ActivityLog>
    suspend fun recentLimited(childId: String, limit: Int): List<ActivityLog>
}

interface DailyPickRepository {
    suspend fun get(childId: String, dateIso: String): DailyPick?
    suspend fun upsert(pick: DailyPick)
}

interface ReminderRepository {
    suspend fun upsert(reminder: Reminder)
    suspend fun getEnabled(): List<Reminder>
    suspend fun getById(id: String): Reminder?
    suspend fun setEnabled(id: String, enabled: Boolean)
    suspend fun insertLog(log: ReminderLog)
    suspend fun shownSuggestedCountToday(dateIso: String): Int
}

interface MilestoneObservationRepository {
    suspend fun forChild(childId: String): Map<String, MilestoneObsStatus>
    suspend fun setStatus(childId: String, milestoneId: String, status: MilestoneObsStatus)
}

interface JournalRepository {
    suspend fun recent(childId: String, limit: Int = 100): List<JournalEntry>
    suspend fun upsert(entry: JournalEntry)
    suspend fun delete(id: String)
}

interface GxLedgerRepository {
    suspend fun balance(childId: String): Int
    suspend fun recent(childId: String, limit: Int = 40): List<GxLedgerEntry>
    suspend fun insert(entry: GxLedgerEntry)
    suspend fun earnedBetween(childId: String, startMs: Long, endMs: Long): Int
    suspend fun countKindRefBetween(
        childId: String,
        kind: String,
        refId: String,
        startMs: Long,
        endMs: Long,
    ): Int
    suspend fun completeEarnCountBetween(childId: String, startMs: Long, endMs: Long): Int
    suspend fun lastCompleteEarnAt(childId: String): Long?
}
