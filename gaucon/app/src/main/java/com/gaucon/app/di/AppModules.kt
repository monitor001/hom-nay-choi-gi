package com.gaucon.app.di

import android.content.Context
import androidx.room.Room
import com.gaucon.core.common.AppClock
import com.gaucon.core.common.SystemAppClock
import com.gaucon.core.database.ActivityLogRepositoryImpl
import com.gaucon.core.database.ActivityRepositoryImpl
import com.gaucon.core.database.ChildRepositoryImpl
import com.gaucon.core.database.DailyPickRepositoryImpl
import com.gaucon.core.database.GauConDatabase
import com.gaucon.core.database.GxLedgerRepositoryImpl
import com.gaucon.core.database.JournalRepositoryImpl
import com.gaucon.core.database.MilestoneObservationRepositoryImpl
import com.gaucon.core.database.ReminderRepositoryImpl
import com.gaucon.core.database.dao.ActivityDao
import com.gaucon.core.database.dao.ActivityLogDao
import com.gaucon.core.database.dao.ChildDao
import com.gaucon.core.database.dao.DailyPickDao
import com.gaucon.core.database.dao.GxLedgerDao
import com.gaucon.core.database.dao.JournalEntryDao
import com.gaucon.core.database.dao.MilestoneStatusDao
import com.gaucon.core.database.dao.ReminderDao
import com.gaucon.core.database.dao.ReminderLogDao
import com.gaucon.core.network.CloudBackupGateway
import com.gaucon.core.network.ContentApi
import com.gaucon.core.network.NoOpCloudBackupGateway
import com.gaucon.core.network.NoOpContentApi
import com.gaucon.domain.picker.DailyPicker
import com.gaucon.domain.repository.ActivityLogRepository
import com.gaucon.domain.repository.ActivityRepository
import com.gaucon.domain.repository.ChildRepository
import com.gaucon.domain.repository.DailyPickRepository
import com.gaucon.domain.repository.GxLedgerRepository
import com.gaucon.domain.repository.JournalRepository
import com.gaucon.domain.repository.MilestoneObservationRepository
import com.gaucon.domain.repository.ReminderRepository
import com.gaucon.domain.usecase.GetTodayPicksUseCase
import com.gaucon.domain.usecase.GxRewardsUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): GauConDatabase =
        Room.databaseBuilder(context, GauConDatabase::class.java, "gaucon.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides fun childDao(db: GauConDatabase): ChildDao = db.childDao()
    @Provides fun activityDao(db: GauConDatabase): ActivityDao = db.activityDao()
    @Provides fun activityLogDao(db: GauConDatabase): ActivityLogDao = db.activityLogDao()
    @Provides fun dailyPickDao(db: GauConDatabase): DailyPickDao = db.dailyPickDao()
    @Provides fun reminderDao(db: GauConDatabase): ReminderDao = db.reminderDao()
    @Provides fun reminderLogDao(db: GauConDatabase): ReminderLogDao = db.reminderLogDao()
    @Provides fun milestoneStatusDao(db: GauConDatabase): MilestoneStatusDao = db.milestoneStatusDao()
    @Provides fun journalEntryDao(db: GauConDatabase): JournalEntryDao = db.journalEntryDao()
    @Provides fun gxLedgerDao(db: GauConDatabase): GxLedgerDao = db.gxLedgerDao()
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds @Singleton abstract fun childRepo(impl: ChildRepositoryImpl): ChildRepository
    @Binds @Singleton abstract fun activityRepo(impl: ActivityRepositoryImpl): ActivityRepository
    @Binds @Singleton abstract fun activityLogRepo(impl: ActivityLogRepositoryImpl): ActivityLogRepository
    @Binds @Singleton abstract fun dailyPickRepo(impl: DailyPickRepositoryImpl): DailyPickRepository
    @Binds @Singleton abstract fun reminderRepo(impl: ReminderRepositoryImpl): ReminderRepository
    @Binds @Singleton abstract fun milestoneObsRepo(impl: MilestoneObservationRepositoryImpl): MilestoneObservationRepository
    @Binds @Singleton abstract fun journalRepo(impl: JournalRepositoryImpl): JournalRepository
    @Binds @Singleton abstract fun gxLedgerRepo(impl: GxLedgerRepositoryImpl): GxLedgerRepository
}

@Module
@InstallIn(SingletonComponent::class)
object AppBindingsModule {
    @Provides
    @Singleton
    fun provideAppClock(): AppClock = SystemAppClock()

    @Provides
    @Singleton
    fun provideDailyPicker(): DailyPicker = DailyPicker()

    @Provides
    @Singleton
    fun provideGetTodayPicks(
        childRepo: ChildRepository,
        activityRepo: ActivityRepository,
        pickRepo: DailyPickRepository,
        logRepo: ActivityLogRepository,
        picker: DailyPicker,
    ): GetTodayPicksUseCase = GetTodayPicksUseCase(
        childRepo = childRepo,
        activityRepo = activityRepo,
        pickRepo = pickRepo,
        logRepo = logRepo,
        picker = picker,
    )

    @Provides
    @Singleton
    fun provideGxRewards(
        gxLedgerRepository: GxLedgerRepository,
        activityLogRepository: ActivityLogRepository,
    ): GxRewardsUseCase = GxRewardsUseCase(
        ledger = gxLedgerRepository,
        activityLogRepository = activityLogRepository,
    )

    @Provides
    @Singleton
    fun provideContentApi(): ContentApi = NoOpContentApi()

    @Provides
    @Singleton
    fun provideCloudBackup(): CloudBackupGateway = NoOpCloudBackupGateway()
}
