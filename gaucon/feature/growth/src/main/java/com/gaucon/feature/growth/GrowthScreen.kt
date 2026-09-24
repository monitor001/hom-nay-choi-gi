package com.gaucon.feature.growth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gaucon.domain.model.MilestoneObsStatus

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GrowthScreen(
    modifier: Modifier = Modifier,
    viewModel: GrowthViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) { viewModel.load() }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 16.dp),
    ) {
        Text("Phát triển", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
        Text("Mỗi bé một nhịp riêng", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(8.dp))
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.secondaryContainer,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                "Gợi ý quan sát — không chẩn đoán. Nếu lo lắng, hãy hỏi bác sĩ. Không so sánh với trẻ khác.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(12.dp),
            )
        }
        Spacer(Modifier.height(12.dp))
        when {
            state.loading -> Text("Đang tải…")
            state.emptyChild -> Text("Chưa có hồ sơ bé — tạo hồ sơ ở bước đầu.")
            else -> {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    item {
                        Card(
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            elevation = CardDefaults.cardElevation(2.dp),
                        ) {
                            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                Text(
                                    "${state.childName} · ${state.ageMonths} tháng",
                                    style = MaterialTheme.typography.titleLarge,
                                )
                                Text(
                                    "Quan sát nhẹ — không xếp hạng.",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                )
                            }
                        }
                    }
                    item {
                        Card(
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            elevation = CardDefaults.cardElevation(2.dp),
                        ) {
                            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                Text("Tuần này", style = MaterialTheme.typography.titleMedium)
                                Text(
                                    "${state.weekActivityCount} hoạt động",
                                    style = MaterialTheme.typography.headlineMedium,
                                )
                                Text(
                                    "Chơi khi cả nhà vui — không phải chỉ tiêu phải đạt.",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                )
                                if (state.domainCounts.isEmpty()) {
                                    AssistChip(onClick = {}, label = { Text("Chưa có dữ liệu tuần này") })
                                } else {
                                    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                        state.domainCounts.forEach { dc ->
                                            AssistChip(
                                                onClick = {},
                                                label = { Text("${dc.label}: ${dc.count}") },
                                                colors = AssistChipDefaults.assistChipColors(
                                                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                                                ),
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                    item {
                        Text("Mốc quan sát", style = MaterialTheme.typography.titleMedium)
                        Text(
                            "Ghi nhận của bạn trên máy này. Không percentile.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                    items(state.milestones, key = { it.id }) { m ->
                        val current = state.statuses[m.id] ?: MilestoneObsStatus.NOT_YET
                        Card(
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            elevation = CardDefaults.cardElevation(1.dp),
                        ) {
                            Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                Text(m.label, style = MaterialTheme.typography.titleMedium)
                                Text(m.band, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.secondary)
                                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                    MilestoneObsStatus.entries.forEach { st ->
                                        FilterChip(
                                            selected = current == st,
                                            onClick = { viewModel.setStatus(m.id, st) },
                                            label = { Text(milestoneStatusLabel(st)) },
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
