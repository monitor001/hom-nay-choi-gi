package com.gaucon.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gaucon.core.database.entity.ActivityEntity
import com.gaucon.core.database.entity.ActivityLogEntity
import com.gaucon.core.database.entity.ChildEntity
import com.gaucon.core.database.entity.DailyPickEntity
import com.gaucon.core.database.entity.ReminderEntity
import com.gaucon.core.database.entity.ReminderLogEntity

@Dao
interface ChildDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(child: ChildEntity)

    @Query("SELECT * FROM child WHERE id = :id LIMIT 1")
    suspend fun getById(id: String): ChildEntity?
}

@Dao
interface ActivityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(items: List<ActivityEntity>)

    @Query("SELECT * FROM activity WHERE isRetired = 0")
    suspend fun getAllActive(): List<ActivityEntity>

    @Query("SELECT * FROM activity WHERE id = :id LIMIT 1")
    suspend fun getById(id: String): ActivityEntity?

    @Query("SELECT * FROM activity WHERE id IN (:ids)")
    suspend fun getByIds(ids: List<String>): List<ActivityEntity>
}

@Dao
interface ActivityLogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(log: ActivityLogEntity)

    @Query(
        """
        SELECT COUNT(*) FROM activity_log
        WHERE childId = :childId
          AND completedAt >= :dayStartMs
          AND completedAt < :dayEndMs
        """,
    )
    suspend fun countBetween(childId: String, dayStartMs: Long, dayEndMs: Long): Int

    @Query(
        """
        SELECT * FROM activity_log
        WHERE childId = :childId AND completedAt >= :sinceEpochMs
        ORDER BY completedAt DESC
        """,
    )
    suspend fun recent(childId: String, sinceEpochMs: Long): List<ActivityLogEntity>
}

@Dao
interface DailyPickDao {
    @Query("SELECT * FROM daily_pick WHERE childId = :childId AND dateIso = :dateIso LIMIT 1")
    suspend fun get(childId: String, dateIso: String): DailyPickEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(pick: DailyPickEntity)
}

@Dao
interface ReminderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(reminder: ReminderEntity)

    @Query("SELECT * FROM reminder WHERE enabled = 1")
    suspend fun getEnabled(): List<ReminderEntity>

    @Query("SELECT * FROM reminder WHERE id = :id LIMIT 1")
    suspend fun getById(id: String): ReminderEntity?

    @Query("UPDATE reminder SET enabled = :enabled WHERE id = :id")
    suspend fun setEnabled(id: String, enabled: Boolean)
}

@Dao
interface ReminderLogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(log: ReminderLogEntity)

    @Query(
        """
        SELECT COUNT(*) FROM reminder_log
        WHERE action = 'SHOWN'
          AND firedAt >= :dayStartMs AND firedAt < :dayEndMs
        """,
    )
    suspend fun shownCountBetween(dayStartMs: Long, dayEndMs: Long): Int
}
