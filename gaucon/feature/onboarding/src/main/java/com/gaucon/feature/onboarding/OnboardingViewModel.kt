package com.gaucon.feature.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaucon.core.datastore.UserPreferences
import com.gaucon.core.notifications.ReminderScheduler
import com.gaucon.domain.model.Child
import com.gaucon.domain.model.Reminder
import com.gaucon.domain.model.ReminderType
import com.gaucon.domain.repository.ChildRepository
import com.gaucon.domain.repository.ReminderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val childRepository: ChildRepository,
    private val reminderRepository: ReminderRepository,
    private val prefs: UserPreferences,
    private val reminderScheduler: ReminderScheduler,
) : ViewModel() {

    fun saveChild(displayName: String, birthDate: LocalDate, onDone: () -> Unit) {
        viewModelScope.launch {
            val id = UUID.randomUUID().toString()
            childRepository.upsert(
                Child(
                    id = id,
                    displayName = displayName,
                    birthDate = birthDate,
                ),
            )
            prefs.setActiveChildId(id)
            val reminderId = DailyActivityIds.DEFAULT_REMINDER_ID
            reminderRepository.upsert(
                Reminder(
                    id = reminderId,
                    childId = id,
                    type = ReminderType.DAILY_ACTIVITY,
                    enabled = true,
                    time = LocalTime.of(19, 30),
                    isUserDefined = false,
                ),
            )
            reminderScheduler.enqueueDailyActivity(reminderId, id, LocalTime.of(19, 30))
            prefs.setOnboardingDone(true)
            onDone()
        }
    }
}

object DailyActivityIds {
    const val DEFAULT_REMINDER_ID = "daily_activity_default"
}
