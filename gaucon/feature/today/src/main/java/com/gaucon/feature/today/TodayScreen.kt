package com.gaucon.feature.today

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.gaucon.domain.model.Activity

@Composable
fun TodayScreen(
    onOpenActivity: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TodayViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) { viewModel.load() }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
    ) {
        Text(
            text = state.greeting,
            style = MaterialTheme.typography.headlineMedium,
        )
        Spacer(Modifier.height(8.dp))
        Text(
            "Nội dung nháp (draft_unreviewed) — chưa chuyên gia duyệt.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.65f),
        )
        if (state.showNotifBanner) {
            Spacer(Modifier.height(12.dp))
            Text(
                "Bạn có thể bật thông báo sau trong phần nhắc — app vẫn dùng được offline.",
                style = MaterialTheme.typography.bodyMedium,
            )
            TextButton(onClick = { viewModel.dismissNotifBanner() }) {
                Text("Để sau")
            }
        }
        Spacer(Modifier.height(16.dp))
        when {
            state.loading -> Text("Đang chuẩn bị gợi ý…")
            state.activities.isEmpty() -> {
                Text(
                    "Hôm nay chưa có gợi ý phù hợp tuổi tháng. Bạn thử lại sau hoặc thêm nội dung khi có bản cập nhật.",
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
            else -> {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(state.activities, key = { it.id }) { activity ->
                        ActivityCard(activity = activity, onClick = { onOpenActivity(activity.id) })
                    }
                    item {
                        TextButton(onClick = { viewModel.refreshSuggestions() }) {
                            Text("Đổi gợi ý")
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ActivityCard(activity: Activity, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
    ) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(activity.title, style = MaterialTheme.typography.titleLarge)
            Text(
                "${activity.durationMinutes} phút · ${activity.domains.firstOrNull()?.name ?: ""}",
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(activity.goal, style = MaterialTheme.typography.bodyLarge)
        }
    }
}
