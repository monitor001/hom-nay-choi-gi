package com.gaucon.feature.growth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaucon.core.common.ageMonths
import com.gaucon.domain.model.Domain
import com.gaucon.domain.model.MilestoneObsStatus
import com.gaucon.domain.model.MilestoneStub
import com.gaucon.domain.repository.ActivityLogRepository
import com.gaucon.domain.repository.ActivityRepository
import com.gaucon.domain.repository.ChildRepository
import com.gaucon.domain.repository.MilestoneObservationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

data class DomainCount(val domain: Domain, val label: String, val count: Int)

data class GrowthUiState(
    val loading: Boolean = true,
    val childName: String? = null,
    val ageMonths: Int? = null,
    val weekActivityCount: Int = 0,
    val domainCounts: List<DomainCount> = emptyList(),
    val milestones: List<MilestoneStub> = MilestoneStubs,
    val statuses: Map<String, MilestoneObsStatus> = emptyMap(),
    val emptyChild: Boolean = false,
)

@HiltViewModel
class GrowthViewModel @Inject constructor(
    private val childRepository: ChildRepository,
    private val activityLogRepository: ActivityLogRepository,
    private val activityRepository: ActivityRepository,
    private val milestoneRepo: MilestoneObservationRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(GrowthUiState())
    val uiState: StateFlow<GrowthUiState> = _uiState.asStateFlow()

    fun load() {
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true) }
            val child = childRepository.getActive()
            if (child == null) {
                _uiState.update { it.copy(loading = false, emptyChild = true) }
                return@launch
            }
            val months = ageMonths(child.birthDate, LocalDate.now())
            val weekAgoMs = System.currentTimeMillis() - 7L * 24 * 60 * 60 * 1000
            val logs = activityLogRepository.recentForChild(child.id, weekAgoMs)
            val activities = activityRepository.getByIds(logs.map { it.activityId }.distinct())
                .associateBy { it.id }
            val counts = mutableMapOf<Domain, Int>()
            logs.forEach { log ->
                val domain = activities[log.activityId]?.domains?.firstOrNull() ?: return@forEach
                counts[domain] = (counts[domain] ?: 0) + 1
            }
            val domainCounts = Domain.entries.mapNotNull { d ->
                val n = counts[d] ?: return@mapNotNull null
                DomainCount(d, domainLabelVi(d), n)
            }
            val statuses = milestoneRepo.forChild(child.id)
            _uiState.update {
                it.copy(
                    loading = false,
                    emptyChild = false,
                    childName = child.displayName,
                    ageMonths = months,
                    weekActivityCount = logs.size,
                    domainCounts = domainCounts,
                    statuses = statuses,
                )
            }
        }
    }

    fun setStatus(milestoneId: String, status: MilestoneObsStatus) {
        viewModelScope.launch {
            val child = childRepository.getActive() ?: return@launch
            milestoneRepo.setStatus(child.id, milestoneId, status)
            _uiState.update { it.copy(statuses = it.statuses + (milestoneId to status)) }
        }
    }
}

fun domainLabelVi(domain: Domain): String = when (domain) {
    Domain.PHYSICAL -> "Vận động"
    Domain.COGNITIVE -> "Nhận thức"
    Domain.LANGUAGE -> "Ngôn ngữ"
    Domain.SOCIAL_EMOTIONAL -> "Xã hội – cảm xúc"
    Domain.AESTHETIC -> "Thẩm mỹ"
    Domain.SELF_CARE -> "Tự chăm sóc"
}

fun milestoneStatusLabel(status: MilestoneObsStatus): String = when (status) {
    MilestoneObsStatus.NOT_YET -> "Chưa thấy"
    MilestoneObsStatus.EMERGING -> "Đang lộ"
    MilestoneObsStatus.OFTEN -> "Thường thấy"
}
