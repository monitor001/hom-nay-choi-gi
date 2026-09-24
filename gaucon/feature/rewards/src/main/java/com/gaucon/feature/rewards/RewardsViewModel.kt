package com.gaucon.feature.rewards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaucon.core.datastore.UserPreferences
import com.gaucon.domain.model.GxLedgerEntry
import com.gaucon.domain.repository.ChildRepository
import com.gaucon.domain.repository.GxLedgerRepository
import com.gaucon.domain.usecase.GxRewardsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class RewardsUiState(
    val loading: Boolean = true,
    val emptyChild: Boolean = false,
    val childName: String? = null,
    val balance: Int = 0,
    val rewardsEnabled: Boolean = true,
    val foodTreatVisible: Boolean = false,
    val items: List<RewardCatalogItem> = emptyList(),
    val recent: List<GxLedgerEntry> = emptyList(),
    val message: String? = null,
)

@HiltViewModel
class RewardsViewModel @Inject constructor(
    private val childRepository: ChildRepository,
    private val gxLedgerRepository: GxLedgerRepository,
    private val gxRewards: GxRewardsUseCase,
    private val prefs: UserPreferences,
) : ViewModel() {

    private val _uiState = MutableStateFlow(RewardsUiState())
    val uiState: StateFlow<RewardsUiState> = _uiState.asStateFlow()

    fun load() {
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true, message = null) }
            val child = childRepository.getActive()
            if (child == null) {
                _uiState.update { it.copy(loading = false, emptyChild = true) }
                return@launch
            }
            val enabled = prefs.rewardsEnabled()
            val food = prefs.foodTreatVisible()
            val balance = gxLedgerRepository.balance(child.id)
            val recent = gxLedgerRepository.recent(child.id, 20)
            val items = RewardCatalog.filter {
                it.tier != RewardTier.FOOD_TREAT || food
            }
            _uiState.update {
                it.copy(
                    loading = false,
                    emptyChild = false,
                    childName = child.displayName,
                    balance = balance,
                    rewardsEnabled = enabled,
                    foodTreatVisible = food,
                    items = items,
                    recent = recent,
                )
            }
        }
    }

    fun setRewardsEnabled(enabled: Boolean) {
        viewModelScope.launch {
            prefs.setRewardsEnabled(enabled)
            _uiState.update { it.copy(rewardsEnabled = enabled) }
        }
    }

    fun setFoodTreatVisible(visible: Boolean) {
        viewModelScope.launch {
            prefs.setFoodTreatVisible(visible)
            _uiState.update {
                it.copy(
                    foodTreatVisible = visible,
                    items = RewardCatalog.filter { item ->
                        item.tier != RewardTier.FOOD_TREAT || visible
                    },
                )
            }
        }
    }

    fun redeem(item: RewardCatalogItem) {
        viewModelScope.launch {
            if (!_uiState.value.rewardsEnabled) {
                _uiState.update { it.copy(message = "Sổ thưởng đang tắt.") }
                return@launch
            }
            val child = childRepository.getActive() ?: return@launch
            val result = gxRewards.redeem(child.id, item.id, item.costGx, item.title)
            _uiState.update {
                it.copy(
                    message = result.message,
                    balance = gxLedgerRepository.balance(child.id),
                    recent = gxLedgerRepository.recent(child.id, 20),
                )
            }
        }
    }
}
