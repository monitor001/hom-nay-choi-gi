package com.gaucon.feature.reminder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaucon.core.notifications.ReminderNotifier
import com.gaucon.core.notifications.ReminderScheduler
import com.gaucon.domain.model.Reminder
import com.gaucon.domain.model.ReminderType
import com.gaucon.domain.repository.ChildRepository
import com.gaucon.domain.repository.ReminderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalTime
import javax.inject.Inject

data class ReminderSettingsUiState(
    val loading: Boolean = true,
    val enabled: Boolean = true,
    val hour: Int = 19,
    val minute: Int = 30,
    val savedMessage: String? = null,
    val childName: String? = null,
)

@HiltViewModel
class ReminderSettingsViewModel @Inject constructor(
    private val reminderRepository: ReminderRepository,
    private val childRepository: ChildRepository,
    private val scheduler: ReminderScheduler,
    private val notifier: ReminderNotifier,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ReminderSettingsUiState())
    val uiState: StateFlow<ReminderSettingsUiState> = _uiState.asStateFlow()

    fun load() {
        viewModelScope.launch {
            val child = childRepository.getActive()
            val reminder = reminderRepository.getById(DEFAULT_REMINDER_ID)
            val time = reminder?.time ?: LocalTime.of(19, 30)
            _uiState.update {
                it.copy(
                    loading = false,
                    enabled = reminder?.enabled != false,
                    hour = time.hour,
                    minute = time.minute,
                    childName = child?.displayName,
                    savedMessage = null,
                )
            }
        }
    }

    fun setEnabled(enabled: Boolean) {
        _uiState.update { it.copy(enabled = enabled, savedMessage = null) }
    }

    fun setHour(hour: Int) {
        _uiState.update { it.copy(hour = hour.coerceIn(0, 23), savedMessage = null) }
    }

    fun setMinute(minute: Int) {
        _uiState.update { it.copy(minute = minute.coerceIn(0, 59), savedMessage = null) }
    }

    fun save() {
        viewModelScope.launch {
            val child = childRepository.getActive() ?: run {
                _uiState.update { it.copy(savedMessage = "Chưa có hồ sơ bé — tạo hồ sơ trước.") }
                return@launch
            }
            val state = _uiState.value
            val time = LocalTime.of(state.hour, state.minute)
            reminderRepository.upsert(
                Reminder(
                    id = DEFAULT_REMINDER_ID,
                    childId = child.id,
                    type = ReminderType.DAILY_ACTIVITY,
                    enabled = state.enabled,
                    time = time,
                    isUserDefined = true,
                ),
            )
            if (state.enabled) {
                scheduler.enqueueDailyActivity(DEFAULT_REMINDER_ID, child.id, time)
            } else {
                scheduler.cancel(DEFAULT_REMINDER_ID)
            }
            _uiState.update {
                it.copy(
                    savedMessage = if (state.enabled) {
                        "Đã lưu — sẽ nhắc lúc %02d:%02d kể cả khi app đóng.".format(state.hour, state.minute)
                    } else {
                        "Đã tắt nhắc định kỳ."
                    },
                )
            }
        }
    }

    fun sendTestNotification() {
        notifier.showTestPing()
        _uiState.update {
            it.copy(savedMessage = "Đã gửi thử — kiểm tra khay thông báo.")
        }
    }

    companion object {
        const val DEFAULT_REMINDER_ID = "daily_activity_default"
    }
}
