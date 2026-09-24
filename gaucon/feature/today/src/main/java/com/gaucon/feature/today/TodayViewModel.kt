package com.gaucon.feature.today

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaucon.contentseed.ContentSeedLoader
import com.gaucon.core.common.ageMonths
import com.gaucon.core.datastore.UserPreferences
import com.gaucon.domain.model.Activity
import com.gaucon.domain.repository.ActivityLogRepository
import com.gaucon.domain.repository.ChildRepository
import com.gaucon.domain.repository.GxLedgerRepository
import com.gaucon.domain.usecase.GetTodayPicksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.ZoneId
import javax.inject.Inject

data class TodayUiState(
    val loading: Boolean = true,
    val greeting: String = "Chào bạn",
    val activities: List<Activity> = emptyList(),
    val completedIds: Set<String> = emptySet(),
    val gxBalance: Int = 0,
    val showNotifBanner: Boolean = false,
    val emptyHint: String? = null,
)

@HiltViewModel
class TodayViewModel @Inject constructor(
    private val getTodayPicks: GetTodayPicksUseCase,
    private val childRepository: ChildRepository,
    private val prefs: UserPreferences,
    private val contentSeedLoader: ContentSeedLoader,
    private val gxLedgerRepository: GxLedgerRepository,
    private val activityLogRepository: ActivityLogRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(TodayUiState())
    val uiState: StateFlow<TodayUiState> = _uiState.asStateFlow()

    private val shownIds = mutableSetOf<String>()

    fun load() {
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true, emptyHint = null) }
            runCatching { contentSeedLoader.upsertIfNewer() }
            val child = childRepository.getActive()
            val months = child?.let { ageMonths(it.birthDate, LocalDate.now()) }
            val greeting = if (child != null && months != null) {
                "Chào bạn — hôm nay cùng ${child.displayName} (${months} tháng)"
            } else {
                "Chào bạn"
            }
            var picks = getTodayPicks()
            if (picks.isEmpty()) {
                runCatching { contentSeedLoader.upsertIfNewer() }
                picks = getTodayPicks()
            }
            shownIds += picks.map { it.id }
            val bannerDue = shouldShowNotifBanner()
            val hint = when {
                picks.isNotEmpty() -> null
                child == null -> "Chưa có hồ sơ bé — hãy tạo hồ sơ trước."
                else -> "Chưa tải được gợi ý. Thử «Đổi gợi ý» hoặc mở lại app."
            }
            val gx = if (child != null && prefs.rewardsEnabled()) {
                gxLedgerRepository.balance(child.id)
            } else {
                0
            }
            val completed = if (child != null) {
                val zone = ZoneId.systemDefault()
                val start = LocalDate.now().atStartOfDay(zone).toInstant().toEpochMilli()
                activityLogRepository.recentForChild(child.id, start).map { it.activityId }.toSet()
            } else {
                emptySet()
            }
            _uiState.update {
                it.copy(
                    loading = false,
                    greeting = greeting,
                    activities = picks,
                    completedIds = completed,
                    gxBalance = gx,
                    showNotifBanner = bannerDue,
                    emptyHint = hint,
                )
            }
        }
    }

    fun refreshSuggestions() {
        viewModelScope.launch {
            runCatching { contentSeedLoader.upsertIfNewer() }
            val picks = getTodayPicks(excludeAlreadyShownIds = shownIds.toSet())
            shownIds += picks.map { it.id }
            _uiState.update {
                it.copy(
                    activities = picks,
                    emptyHint = if (picks.isEmpty()) {
                        "Chưa có gợi ý khác hôm nay. Thử lại sau."
                    } else {
                        null
                    },
                )
            }
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
