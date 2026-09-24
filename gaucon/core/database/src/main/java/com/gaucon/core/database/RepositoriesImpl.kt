package com.gaucon.core.database

import com.gaucon.core.database.dao.ActivityDao
import com.gaucon.core.database.dao.ActivityLogDao
import com.gaucon.core.database.dao.ChildDao
import com.gaucon.core.database.dao.DailyPickDao
import com.gaucon.core.database.dao.GxLedgerDao
import com.gaucon.core.database.dao.JournalEntryDao
import com.gaucon.core.database.dao.MilestoneStatusDao
import com.gaucon.core.database.dao.ReminderDao
import com.gaucon.core.database.dao.ReminderLogDao
import com.gaucon.core.datastore.UserPreferences
import com.gaucon.domain.model.Activity
import com.gaucon.domain.model.ActivityLog
import com.gaucon.domain.model.Child
import com.gaucon.domain.model.DailyPick
import com.gaucon.domain.model.GxLedgerEntry
import com.gaucon.domain.model.JournalEntry
import com.gaucon.domain.model.MilestoneObsStatus
import com.gaucon.domain.model.MilestoneObservation
import com.gaucon.domain.model.Reminder
import com.gaucon.domain.model.ReminderLog
import com.gaucon.domain.repository.ActivityLogRepository
import com.gaucon.domain.repository.ActivityRepository
import com.gaucon.domain.repository.ChildRepository
import com.gaucon.domain.repository.DailyPickRepository
import com.gaucon.domain.repository.GxLedgerRepository
import com.gaucon.domain.repository.JournalRepository
import com.gaucon.domain.repository.MilestoneObservationRepository
import com.gaucon.domain.repository.ReminderRepository
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChildRepositoryImpl @Inject constructor(
    private val childDao: ChildDao,
    private val prefs: UserPreferences,
) : ChildRepository {
    override suspend fun upsert(child: Child) = childDao.upsert(child.toEntity())
    override suspend fun getById(id: String): Child? = childDao.getById(id)?.toModel()
    override suspend fun getActive(): Child? {
        val id = prefs.activeChildId() ?: return null
        return getById(id)
    }
}

@Singleton
class ActivityRepositoryImpl @Inject constructor(
    private val activityDao: ActivityDao,
) : ActivityRepository {
    override suspend fun upsertAll(activities: List<Activity>) =
        activityDao.upsertAll(activities.map { it.toEntity() })

    override suspend fun getAllActive(): List<Activity> =
        activityDao.getAllActive().map { it.toModel() }

    override suspend fun getById(id: String): Activity? =
        activityDao.getById(id)?.toModel()

    override suspend fun getByIds(ids: List<String>): List<Activity> {
        if (ids.isEmpty()) return emptyList()
        val byId = activityDao.getByIds(ids).map { it.toModel() }.associateBy { it.id }
        return ids.mapNotNull { byId[it] }
    }
}

@Singleton
class ActivityLogRepositoryImpl @Inject constructor(
    private val logDao: ActivityLogDao,
) : ActivityLogRepository {
    override suspend fun insert(log: ActivityLog) = logDao.insert(log.toEntity())

    override suspend fun completedCountToday(childId: String, dateIso: String): Int {
        val day = LocalDate.parse(dateIso)
        val zone = ZoneId.systemDefault()
        val start = day.atStartOfDay(zone).toInstant().toEpochMilli()
        val end = day.plusDays(1).atStartOfDay(zone).toInstant().toEpochMilli()
        return logDao.countBetween(childId, start, end)
    }

    override suspend fun recentForChild(childId: String, sinceEpochMs: Long): List<ActivityLog> =
        logDao.recent(childId, sinceEpochMs).map { it.toModel() }

    override suspend fun recentLimited(childId: String, limit: Int): List<ActivityLog> =
        logDao.recentLimited(childId, limit).map { it.toModel() }
}

@Singleton
class DailyPickRepositoryImpl @Inject constructor(
    private val pickDao: DailyPickDao,
) : DailyPickRepository {
    override suspend fun get(childId: String, dateIso: String): DailyPick? =
        pickDao.get(childId, dateIso)?.toModel()

    override suspend fun upsert(pick: DailyPick) = pickDao.upsert(pick.toEntity())
}

@Singleton
class ReminderRepositoryImpl @Inject constructor(
    private val reminderDao: ReminderDao,
    private val reminderLogDao: ReminderLogDao,
) : ReminderRepository {
    override suspend fun upsert(reminder: Reminder) = reminderDao.upsert(reminder.toEntity())
    override suspend fun getEnabled(): List<Reminder> = reminderDao.getEnabled().map { it.toModel() }
    override suspend fun getById(id: String): Reminder? = reminderDao.getById(id)?.toModel()
    override suspend fun setEnabled(id: String, enabled: Boolean) = reminderDao.setEnabled(id, enabled)
    override suspend fun insertLog(log: ReminderLog) = reminderLogDao.insert(log.toEntity())

    override suspend fun shownSuggestedCountToday(dateIso: String): Int {
        val day = LocalDate.parse(dateIso)
        val zone = ZoneId.systemDefault()
        val start = day.atStartOfDay(zone).toInstant().toEpochMilli()
        val end = day.plusDays(1).atStartOfDay(zone).toInstant().toEpochMilli()
        return reminderLogDao.shownCountBetween(start, end)
    }
}

@Singleton
class MilestoneObservationRepositoryImpl @Inject constructor(
    private val dao: MilestoneStatusDao,
) : MilestoneObservationRepository {
    override suspend fun forChild(childId: String): Map<String, MilestoneObsStatus> =
        dao.forChild(childId).associate { it.milestoneId to it.toStatus() }

    override suspend fun setStatus(childId: String, milestoneId: String, status: MilestoneObsStatus) {
        dao.upsert(
            MilestoneObservation(
                childId = childId,
                milestoneId = milestoneId,
                status = status,
                updatedAt = Instant.now(),
            ).toEntity(),
        )
    }
}

@Singleton
class JournalRepositoryImpl @Inject constructor(
    private val dao: JournalEntryDao,
) : JournalRepository {
    override suspend fun recent(childId: String, limit: Int): List<JournalEntry> =
        dao.recent(childId, limit).map { it.toModel() }

    override suspend fun upsert(entry: JournalEntry) = dao.upsert(entry.toEntity())

    override suspend fun delete(id: String) = dao.delete(id)
}

@Singleton
class GxLedgerRepositoryImpl @Inject constructor(
    private val dao: GxLedgerDao,
) : GxLedgerRepository {
    override suspend fun balance(childId: String): Int = dao.balance(childId)

    override suspend fun recent(childId: String, limit: Int): List<GxLedgerEntry> =
        dao.recent(childId, limit).map { it.toModel() }

    override suspend fun insert(entry: GxLedgerEntry) = dao.insert(entry.toEntity())

    override suspend fun earnedBetween(childId: String, startMs: Long, endMs: Long): Int =
        dao.earnedBetween(childId, startMs, endMs)

    override suspend fun countKindRefBetween(
        childId: String,
        kind: String,
        refId: String,
        startMs: Long,
        endMs: Long,
    ): Int = dao.countKindRefBetween(childId, kind, refId, startMs, endMs)

    override suspend fun completeEarnCountBetween(childId: String, startMs: Long, endMs: Long): Int =
        dao.completeEarnCountBetween(childId, startMs, endMs)

    override suspend fun lastCompleteEarnAt(childId: String): Long? =
        dao.lastCompleteEarnAt(childId)
}
