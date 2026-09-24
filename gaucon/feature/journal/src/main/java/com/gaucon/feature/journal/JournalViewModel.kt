package com.gaucon.feature.journal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaucon.domain.model.JournalEntry
import com.gaucon.domain.repository.ActivityLogRepository
import com.gaucon.domain.repository.ActivityRepository
import com.gaucon.domain.repository.ChildRepository
import com.gaucon.domain.repository.JournalRepository
import com.gaucon.domain.usecase.GxRewardsUseCase
import com.gaucon.core.datastore.UserPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.UUID
import javax.inject.Inject

enum class TimelineKind { ACTIVITY, NOTE }

data class TimelineItem(
    val id: String,
    val kind: TimelineKind,
    val title: String,
    val subtitle: String,
    val at: Instant,
)

data class JournalUiState(
    val loading: Boolean = true,
    val childName: String? = null,
    val emptyChild: Boolean = false,
    val items: List<TimelineItem> = emptyList(),
    val draftText: String = "",
    val saving: Boolean = false,
    val message: String? = null,
)

@HiltViewModel
class JournalViewModel @Inject constructor(
    private val childRepository: ChildRepository,
    private val journalRepository: JournalRepository,
    private val activityLogRepository: ActivityLogRepository,
    private val activityRepository: ActivityRepository,
    private val gxRewards: GxRewardsUseCase,
    private val prefs: UserPreferences,
) : ViewModel() {

    private val _uiState = MutableStateFlow(JournalUiState())
    val uiState: StateFlow<JournalUiState> = _uiState.asStateFlow()

    private val timeFmt = DateTimeFormatter.ofPattern("dd/MM HH:mm")
        .withZone(ZoneId.systemDefault())

    fun load() {
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true, message = null) }
            val child = childRepository.getActive()
            if (child == null) {
                _uiState.update { it.copy(loading = false, emptyChild = true) }
                return@launch
            }
            val notes = journalRepository.recent(child.id, 80)
            val logs = activityLogRepository.recentLimited(child.id, 80)
            val activities = activityRepository.getByIds(logs.map { it.activityId }.distinct())
                .associateBy { it.id }
            val timeline = buildList {
                notes.forEach { n ->
                    add(
                        TimelineItem(
                            id = n.id,
                            kind = TimelineKind.NOTE,
                            title = "Khoảnh khắc",
                            subtitle = n.text,
                            at = n.createdAt,
                        ),
                    )
                }
                logs.forEach { log ->
                    val title = activities[log.activityId]?.title ?: "Hoạt động"
                    add(
                        TimelineItem(
                            id = log.id,
                            kind = TimelineKind.ACTIVITY,
                            title = "Đã chơi: $title",
                            subtitle = timeFmt.format(log.completedAt),
                            at = log.completedAt,
                        ),
                    )
                }
            }.sortedByDescending { it.at }
            _uiState.update {
                it.copy(
                    loading = false,
                    emptyChild = false,
                    childName = child.displayName,
                    items = timeline,
                )
            }
        }
    }

    fun onDraftChange(text: String) {
        _uiState.update { it.copy(draftText = text, message = null) }
    }

    fun saveNote() {
        viewModelScope.launch {
            val text = _uiState.value.draftText.trim()
            if (text.isEmpty()) {
                _uiState.update { it.copy(message = "Viết một dòng trước khi lưu.") }
                return@launch
            }
            val child = childRepository.getActive() ?: run {
                _uiState.update { it.copy(message = "Chưa có hồ sơ bé.") }
                return@launch
            }
            _uiState.update { it.copy(saving = true) }
            val entryId = UUID.randomUUID().toString()
            journalRepository.upsert(
                JournalEntry(
                    id = entryId,
                    childId = child.id,
                    createdAt = Instant.now(),
                    text = text,
                ),
            )
            val earnExtra = if (prefs.rewardsEnabled()) {
                gxRewards.onJournalSaved(child.id, entryId).message
            } else {
                null
            }
            val msg = listOfNotNull("Đã lưu khoảnh khắc.", earnExtra).joinToString(" ")
            _uiState.update { it.copy(saving = false, draftText = "", message = msg) }
            load()
        }
    }
}
