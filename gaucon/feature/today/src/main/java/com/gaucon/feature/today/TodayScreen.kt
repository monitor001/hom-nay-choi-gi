package com.gaucon.feature.today

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gaucon.core.designsystem.R as DesignR
import com.gaucon.domain.model.Activity
import com.gaucon.domain.model.Domain
import com.gaucon.feature.rewards.GxBalanceChip

@Composable
fun TodayScreen(
    onOpenActivity: (String) -> Unit,
    onOpenShop: () -> Unit = {},
    modifier: Modifier = Modifier,
    viewModel: TodayViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                viewModel.load()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 20.dp, vertical = 16.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Image(
                painter = painterResource(id = DesignR.drawable.ic_logo),
                contentDescription = "Hôm nay chơi gì?",
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
            )
            Column(Modifier.weight(1f)) {
                Text(
                    text = "Hôm nay chơi gì?",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                )
                Text(
                    text = state.greeting,
                    style = MaterialTheme.typography.titleLarge,
                )
            }
            GxBalanceChip(
                balance = state.gxBalance,
                onClick = onOpenShop,
            )
        }
        Spacer(Modifier.height(8.dp))
        Text(
            "Nháp nội bộ — chưa chuyên gia duyệt.",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        if (state.showNotifBanner) {
            Spacer(Modifier.height(12.dp))
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(Modifier.padding(14.dp)) {
                    Text(
                        "Có thể bật nhắc sau — app dùng offline được.",
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    TextButton(onClick = { viewModel.dismissNotifBanner() }) {
                        Text("Để sau")
                    }
                }
            }
        }
        Spacer(Modifier.height(16.dp))
        when {
            state.loading -> Text("Đang chuẩn bị gợi ý…")
            state.activities.isEmpty() -> {
                Text(
                    state.emptyHint
                        ?: "Hôm nay chưa có gợi ý. Thử «Đổi gợi ý» hoặc mở lại app.",
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
            else -> {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(state.activities, key = { it.id }) { activity ->
                        ActivityCard(
                            activity = activity,
                            done = activity.id in state.completedIds,
                            onClick = { onOpenActivity(activity.id) },
                        )
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
private fun ActivityCard(activity: Activity, done: Boolean, onClick: () -> Unit) {
    val domain = activity.domains.firstOrNull()
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 72.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (done) {
                MaterialTheme.colorScheme.primaryContainer
            } else {
                MaterialTheme.colorScheme.surface
            },
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(activity.title, style = MaterialTheme.typography.titleLarge, modifier = Modifier.weight(1f))
                if (done) {
                    Text(
                        "Đã chơi ✓",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AssistChip(
                    onClick = onClick,
                    label = { Text("${activity.durationMinutes} phút") },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        labelColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    ),
                )
                if (domain != null) {
                    AssistChip(
                        onClick = onClick,
                        label = { Text(domainLabelVi(domain)) },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                            labelColor = MaterialTheme.colorScheme.onTertiaryContainer,
                        ),
                    )
                }
            }
            Text(activity.goal, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

private fun domainLabelVi(domain: Domain): String = when (domain) {
    Domain.PHYSICAL -> "Vận động"
    Domain.COGNITIVE -> "Nhận thức"
    Domain.LANGUAGE -> "Ngôn ngữ"
    Domain.SOCIAL_EMOTIONAL -> "Xã hội – cảm xúc"
    Domain.AESTHETIC -> "Thẩm mỹ"
    Domain.SELF_CARE -> "Tự chăm sóc"
}
