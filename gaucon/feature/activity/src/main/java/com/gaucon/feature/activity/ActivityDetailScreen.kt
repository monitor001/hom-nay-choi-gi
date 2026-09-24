package com.gaucon.feature.activity

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ActivityDetailScreen(
    activityId: String,
    onBack: () -> Unit,
    onCompleted: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ActivityViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(activityId) { viewModel.load(activityId) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        TextButton(onClick = onBack) { Text("← Quay lại") }
        val activity = state.activity
        if (activity == null) {
            Text(if (state.loading) "Đang tải…" else "Không tìm thấy hoạt động.")
            return
        }
        Text(activity.title, style = MaterialTheme.typography.headlineMedium)
        Text(
            "${activity.durationMinutes} phút · ${activity.domains.joinToString { it.name }}",
            style = MaterialTheme.typography.bodyMedium,
        )
        Text(
            "Bản nháp draft_unreviewed — chưa chuyên gia duyệt.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.65f),
        )
        Spacer(Modifier.height(4.dp))
        Text("Mục tiêu", style = MaterialTheme.typography.titleMedium)
        Text(activity.goal, style = MaterialTheme.typography.bodyLarge)
        Text("Chuẩn bị", style = MaterialTheme.typography.titleMedium)
        activity.materials.forEach { Text("• $it", style = MaterialTheme.typography.bodyLarge) }
        Text("Các bước", style = MaterialTheme.typography.titleMedium)
        activity.steps.forEachIndexed { index, step ->
            Text("${index + 1}. $step", style = MaterialTheme.typography.bodyLarge)
        }
        if (!activity.safety.isNullOrBlank()) {
            Text("An toàn", style = MaterialTheme.typography.titleMedium)
            Text(activity.safety!!, style = MaterialTheme.typography.bodyLarge)
        }
        Spacer(Modifier.height(12.dp))
        Button(
            onClick = {
                viewModel.complete {
                    onCompleted()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.completing,
        ) {
            Text("Hoàn thành")
        }
    }
}
