package com.gaucon.feature.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaucon.domain.model.Activity
import com.gaucon.domain.model.ActivityLog
import com.gaucon.domain.repository.ActivityLogRepository
import com.gaucon.domain.repository.ActivityRepository
import com.gaucon.domain.repository.ChildRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.UUID
import javax.inject.Inject

data class ActivityUiState(
    val loading: Boolean = true,
    val activity: Activity? = null,
    val completing: Boolean = false,
)

@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val activityRepository: ActivityRepository,
    private val activityLogRepository: ActivityLogRepository,
    private val childRepository: ChildRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ActivityUiState())
    val uiState: StateFlow<ActivityUiState> = _uiState.asStateFlow()

    fun load(activityId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true) }
            val activity = activityRepository.getById(activityId)
            _uiState.update { it.copy(loading = false, activity = activity) }
        }
    }

    fun complete(onDone: () -> Unit) {
        viewModelScope.launch {
            val activity = _uiState.value.activity ?: return@launch
            val child = childRepository.getActive() ?: return@launch
            _uiState.update { it.copy(completing = true) }
            activityLogRepository.insert(
                ActivityLog(
                    id = UUID.randomUUID().toString(),
                    childId = child.id,
                    activityId = activity.id,
                    completedAt = Instant.now(),
                    feedback = null,
                ),
            )
            _uiState.update { it.copy(completing = false) }
            onDone()
        }
    }
}
