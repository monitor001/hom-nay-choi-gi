package com.gaucon.feature.journal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun JournalScreen(
    modifier: Modifier = Modifier,
    viewModel: JournalViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) { viewModel.load() }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 16.dp),
    ) {
        Text("Nhật ký", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
        Text(
            if (state.childName != null) "Khoảnh khắc cùng ${state.childName}" else "Khoảnh khắc cùng con",
            style = MaterialTheme.typography.headlineMedium,
        )
        Spacer(Modifier.height(8.dp))
        Text(
            "Ghi nhẹ — kỷ niệm, không chấm điểm bé.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(Modifier.height(12.dp))
        when {
            state.loading -> Text("Đang tải…")
            state.emptyChild -> Text("Chưa có hồ sơ bé — tạo hồ sơ trước.")
            else -> {
                OutlinedTextField(
                    value = state.draftText,
                    onValueChange = viewModel::onDraftChange,
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 2,
                    label = { Text("Từ mới / khoảnh khắc hôm nay") },
                    placeholder = { Text("Vd: Bé chỉ quả chuối và cười…") },
                    shape = RoundedCornerShape(12.dp),
                )
                Spacer(Modifier.height(8.dp))
                Button(
                    onClick = { viewModel.saveNote() },
                    enabled = !state.saving,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(if (state.saving) "Đang lưu…" else "Lưu vào nhật ký")
                }
                state.message?.let {
                    Text(it, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.secondary)
                }
                Spacer(Modifier.height(16.dp))
                if (state.items.isEmpty()) {
                    Text(
                        "Chưa có gì trong nhật ký. Lưu một khoảnh khắc hoặc hoàn thành một hoạt động nhé.",
                        style = MaterialTheme.typography.bodyLarge,
                    )
                } else {
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        items(state.items, key = { it.id }) { item ->
                            Card(
                                colors = CardDefaults.cardColors(
                                    containerColor = if (item.kind == TimelineKind.NOTE) {
                                        MaterialTheme.colorScheme.primaryContainer
                                    } else {
                                        MaterialTheme.colorScheme.surface
                                    },
                                ),
                                elevation = CardDefaults.cardElevation(1.dp),
                            ) {
                                Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                    Text(item.title, style = MaterialTheme.typography.titleMedium)
                                    Text(item.subtitle, style = MaterialTheme.typography.bodyLarge)
                                    if (item.kind == TimelineKind.NOTE) {
                                        Text(
                                            java.time.format.DateTimeFormatter.ofPattern("dd/MM HH:mm")
                                                .withZone(java.time.ZoneId.systemDefault())
                                                .format(item.at),
                                            style = MaterialTheme.typography.labelMedium,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant,
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
