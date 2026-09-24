package com.gaucon.feature.activity

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gaucon.contentseed.ResourceItem
import com.gaucon.core.designsystem.FullscreenImageViewer
import com.gaucon.core.designsystem.ZoomableAssetImage
import com.gaucon.core.designsystem.rememberAssetImageBitmap
import com.gaucon.feature.rewards.R as RewardsR

@Composable
fun ActivityDetailScreen(
    activityId: String,
    onBack: () -> Unit,
    onCompleted: () -> Unit,
    onOpenShop: () -> Unit = {},
    onOpenResource: (String) -> Unit = {},
    modifier: Modifier = Modifier,
    viewModel: ActivityViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(activityId) { viewModel.load(activityId) }

    if (state.completed) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = RewardsR.drawable.ic_gau_xu),
                contentDescription = "Gấu Xu",
                modifier = Modifier
                    .size(96.dp)
                    .clip(RoundedCornerShape(48.dp)),
                contentScale = ContentScale.Crop,
            )
            Spacer(Modifier.height(16.dp))
            Text("Đã chơi xong!", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(8.dp))
            Text(
                state.earnMessage ?: "Đã ghi nhận buổi chơi.",
                style = MaterialTheme.typography.bodyLarge,
            )
            if (state.gxAwarded > 0) {
                Spacer(Modifier.height(8.dp))
                Text(
                    "Số dư: ${state.gxBalance} GX",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
            Spacer(Modifier.height(24.dp))
            Button(onClick = onCompleted, modifier = Modifier.fillMaxWidth()) {
                Text("Về Hôm nay")
            }
            TextButton(onClick = onOpenShop) {
                Text("Mở cửa hàng đổi thưởng")
            }
        }
        return
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TextButton(onClick = onBack) { Text("← Quay lại") }
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Image(
                    painter = painterResource(id = RewardsR.drawable.ic_gau_xu),
                    contentDescription = null,
                    modifier = Modifier.size(22.dp).clip(RoundedCornerShape(99.dp)),
                    contentScale = ContentScale.Crop,
                )
                Text("${state.gxBalance} GX", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            }
        }
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
            "Nháp nội bộ — chưa chuyên gia duyệt.",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
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
        if (state.linkedResources.isNotEmpty()) {
            Text("Tranh / tài liệu cho bé", style = MaterialTheme.typography.titleMedium)
            Text(
                "Chạm tranh hoặc «Xem toàn màn hình» để bé xem rõ.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            state.linkedResources.forEach { res ->
                LinkedResourceCard(
                    res = res,
                    onOpenResource = { onOpenResource(res.id) },
                )
            }
        }
        Spacer(Modifier.height(12.dp))
        Button(
            onClick = { viewModel.complete() },
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.completing,
        ) {
            Text(
                when {
                    state.completing -> "Đang ghi…"
                    state.rewardsEnabled -> "Hoàn thành · nhận Gấu Xu"
                    else -> "Hoàn thành"
                },
            )
        }
    }
}

@Composable
private fun LinkedResourceCard(
    res: ResourceItem,
    onOpenResource: () -> Unit,
) {
    var fullscreen by remember(res.id) { mutableStateOf(false) }
    val bitmap = rememberAssetImageBitmap(res.imageAssetPath)
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(
            Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            if (bitmap != null) {
                ZoomableAssetImage(
                    assetPath = res.imageAssetPath,
                    contentDescription = res.title,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(14.dp)),
                )
            }
            Text(res.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            Text(res.categoryLabel, style = MaterialTheme.typography.labelMedium)
            if (bitmap != null) {
                Button(
                    onClick = { fullscreen = true },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text("Xem tranh toàn màn hình", fontWeight = FontWeight.Bold)
                }
            }
            OutlinedButton(onClick = onOpenResource, modifier = Modifier.fillMaxWidth()) {
                Text("Mở trang tài liệu")
            }
        }
    }
    if (fullscreen && bitmap != null) {
        FullscreenImageViewer(
            image = bitmap,
            title = res.title,
            onDismiss = { fullscreen = false },
        )
    }
}
