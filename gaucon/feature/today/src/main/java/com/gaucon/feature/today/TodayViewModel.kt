package com.gaucon.feature.today

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaucon.core.common.ageMonths
import com.gaucon.core.datastore.UserPreferences
import com.gaucon.domain.model.Activity
import com.gaucon.domain.repository.ChildRepository
import com.gaucon.domain.usecase.GetTodayPicksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

data class TodayUiState(
    val loading: Boolean = true,
    val greeting: String = "Chào bạn",
    val activities: List<Activity> = emptyList(),
    val showNotifBanner: Boolean = false,
)

@HiltViewModel
class TodayViewModel @Inject constructor(
    private val getTodayPicks: GetTodayPicksUseCase,
    private val childRepository: ChildRepository,
    private val prefs: UserPreferences,
) : ViewModel() {

    private val _uiState = MutableStateFlow(TodayUiState())
    val uiState: StateFlow<TodayUiState> = _uiState.asStateFlow()

    private val shownIds = mutableSetOf<String>()

    fun load() {
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true) }
            val child = childRepository.getActive()
            val months = child?.let { ageMonths(it.birthDate, LocalDate.now()) }
            val greeting = if (child != null && months != null) {
                "Chào bạn — hôm nay cùng ${child.displayName} (${months} tháng)"
            } else {
                "Chào bạn"
            }
            val picks = getTodayPicks()
            shownIds += picks.map { it.id }
            val bannerDue = shouldShowNotifBanner()
            _uiState.update {
                it.copy(
                    loading = false,
                    greeting = greeting,
                    activities = picks,
                    showNotifBanner = bannerDue,
                )
            }
        }
    }

    fun refreshSuggestions() {
        viewModelScope.launch {
            val picks = getTodayPicks(excludeAlreadyShownIds = shownIds.toSet())
            shownIds += picks.map { it.id }
            _uiState.update { it.copy(activities = picks) }
        }
    }

    fun dismissNotifBanner() {
        viewModelScope.launch {
            prefs.setNotifBannerLastShownAt(System.currentTimeMillis())
            _uiState.update { it.copy(showNotifBanner = false) }
        }
    }

    private suspend fun shouldShowNotifBanner(): Boolean {
        val last = prefs.notifBannerLastShownAt()
        val weekMs = 7L * 24 * 60 * 60 * 1000
        return System.currentTimeMillis() - last >= weekMs
    }
}
