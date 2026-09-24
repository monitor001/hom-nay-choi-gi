package com.gaucon.core.database

import com.gaucon.core.database.dao.ActivityDao
import com.gaucon.core.database.dao.ActivityLogDao
import com.gaucon.core.database.dao.ChildDao
import com.gaucon.core.database.dao.DailyPickDao
import com.gaucon.core.database.dao.ReminderDao
import com.gaucon.core.database.dao.ReminderLogDao
import com.gaucon.core.datastore.UserPreferences
import com.gaucon.domain.model.Activity
import com.gaucon.domain.model.ActivityLog
import com.gaucon.domain.model.Child
import com.gaucon.domain.model.DailyPick
import com.gaucon.domain.model.Reminder
import com.gaucon.domain.model.ReminderLog
import com.gaucon.domain.repository.ActivityLogRepository
import com.gaucon.domain.repository.ActivityRepository
import com.gaucon.domain.repository.ChildRepository
import com.gaucon.domain.repository.DailyPickRepository
import com.gaucon.domain.repository.ReminderRepository
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
