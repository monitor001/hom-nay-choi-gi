package com.gaucon.feature.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaucon.contentseed.ResourceCatalogLoader
import com.gaucon.contentseed.ResourceItem
import com.gaucon.core.datastore.UserPreferences
import com.gaucon.domain.model.Activity
import com.gaucon.domain.model.ActivityLog
import com.gaucon.domain.repository.ActivityLogRepository
import com.gaucon.domain.repository.ActivityRepository
import com.gaucon.domain.repository.ChildRepository
import com.gaucon.domain.repository.GxLedgerRepository
import com.gaucon.domain.usecase.GxRewardsUseCase
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
    val linkedResources: List<ResourceItem> = emptyList(),
    val completing: Boolean = false,
    val completed: Boolean = false,
    val rewardsEnabled: Boolean = true,
    val earnMessage: String? = null,
    val gxBalance: Int = 0,
    val gxAwarded: Int = 0,
)

@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val activityRepository: ActivityRepository,
    private val activityLogRepository: ActivityLogRepository,
    private val childRepository: ChildRepository,
    private val resourceCatalogLoader: ResourceCatalogLoader,
    private val gxRewards: GxRewardsUseCase,
    private val gxLedgerRepository: GxLedgerRepository,
    private val prefs: UserPreferences,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ActivityUiState())
    val uiState: StateFlow<ActivityUiState> = _uiState.asStateFlow()

    fun load(activityId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true, earnMessage = null, completed = false) }
            val activity = activityRepository.getById(activityId)
            val linked = runCatching { resourceCatalogLoader.linkedForActivity(activityId) }
                .getOrDefault(emptyList())
            val child = childRepository.getActive()
            val bal = if (child != null) gxLedgerRepository.balance(child.id) else 0
            val rewardsOn = prefs.rewardsEnabled()
            _uiState.update {
                it.copy(
                    loading = false,
                    activity = activity,
                    linkedResources = linked,
                    gxBalance = bal,
                    rewardsEnabled = rewardsOn,
                )
            }
        }
    }

    fun complete() {
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
            val earn = if (prefs.rewardsEnabled()) {
                gxRewards.onActivityCompleted(child.id, activity.id)
            } else {
                GxRewardsUseCase.EarnResult(0, null)
            }
            val bal = gxLedgerRepository.balance(child.id)
            _uiState.update {
                it.copy(
                    completing = false,
                    completed = true,
                    earnMessage = earn.message ?: if (earn.awarded > 0) "+${earn.awarded} GX" else "Đã ghi nhận buổi chơi.",
                    gxAwarded = earn.awarded,
                    gxBalance = bal,
                )
            }
        }
    }
}
