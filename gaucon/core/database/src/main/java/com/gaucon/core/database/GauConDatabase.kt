package com.gaucon.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gaucon.core.database.dao.ActivityDao
import com.gaucon.core.database.dao.ActivityLogDao
import com.gaucon.core.database.dao.ChildDao
import com.gaucon.core.database.dao.DailyPickDao
import com.gaucon.core.database.dao.GxLedgerDao
import com.gaucon.core.database.dao.JournalEntryDao
import com.gaucon.core.database.dao.MilestoneStatusDao
import com.gaucon.core.database.dao.ReminderDao
import com.gaucon.core.database.dao.ReminderLogDao
import com.gaucon.core.database.entity.ActivityEntity
import com.gaucon.core.database.entity.ActivityLogEntity
import com.gaucon.core.database.entity.ChildEntity
import com.gaucon.core.database.entity.DailyPickEntity
import com.gaucon.core.database.entity.GxLedgerEntity
import com.gaucon.core.database.entity.JournalEntryEntity
import com.gaucon.core.database.entity.MilestoneStatusEntity
import com.gaucon.core.database.entity.ReminderEntity
import com.gaucon.core.database.entity.ReminderLogEntity

@Database(
    entities = [
        ChildEntity::class,
        ActivityEntity::class,
        ActivityLogEntity::class,
        DailyPickEntity::class,
        ReminderEntity::class,
        ReminderLogEntity::class,
        MilestoneStatusEntity::class,
        JournalEntryEntity::class,
        GxLedgerEntity::class,
    ],
    version = 3,
    exportSchema = false,
)
abstract class GauConDatabase : RoomDatabase() {
    abstract fun childDao(): ChildDao
    abstract fun activityDao(): ActivityDao
    abstract fun activityLogDao(): ActivityLogDao
    abstract fun dailyPickDao(): DailyPickDao
    abstract fun reminderDao(): ReminderDao
    abstract fun reminderLogDao(): ReminderLogDao
    abstract fun milestoneStatusDao(): MilestoneStatusDao
    abstract fun journalEntryDao(): JournalEntryDao
    abstract fun gxLedgerDao(): GxLedgerDao
}
