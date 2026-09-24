package com.gaucon.feature.rewards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.collectAsStateWithLifecycle

private val ShelfBg = Color(0xFFE8F2F0)
private val TileBorder = Color(0xFFC5D9D5)
private val PriceGold = Color(0xFF8A5A00)
private val PriceBg = Color(0xFFFFF3D6)

private sealed class ShopGridRow {
    data class Header(val tier: RewardTier) : ShopGridRow()
    data class Item(val item: RewardCatalogItem) : ShopGridRow()
    data object RecentTitle : ShopGridRow()
    data class Recent(val text: String, val id: String) : ShopGridRow()
}

@Composable
fun RewardsScreen(
    onBack: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    viewModel: RewardsViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) viewModel.load()
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }

    val rows = buildList {
        var last: RewardTier? = null
        state.items.forEach { item ->
            if (item.tier != last) {
                add(ShopGridRow.Header(item.tier))
                last = item.tier
            }
            add(ShopGridRow.Item(item))
        }
        if (state.recent.isNotEmpty()) {
            add(ShopGridRow.RecentTitle)
            state.recent.take(6).forEach { entry ->
                add(
                    ShopGridRow.Recent(
                        id = entry.id,
                        text = "${if (entry.amount >= 0) "+" else ""}${entry.amount} · ${entry.note ?: entry.kind}",
                    ),
                )
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ShelfBg)
            .padding(horizontal = 12.dp, vertical = 10.dp),
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(Modifier.weight(1f)) {
                Text(
                    "Cửa hàng",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                )
                Text(
                    "Đổi Gấu Xu",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                )
            }
            GxBalanceChip(balance = state.balance)
        }
        Spacer(Modifier.height(6.dp))
        Text(
            "Chọn ô quà — PH chuẩn bị khi đổi.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(Modifier.height(8.dp))

        when {
            state.loading -> Text("Đang mở cửa hàng…")
            state.emptyChild -> Text("Chưa có hồ sơ bé.")
            else -> {
                ShopSettingsBar(
                    rewardsEnabled = state.rewardsEnabled,
                    foodTreatVisible = state.foodTreatVisible,
                    onRewards = viewModel::setRewardsEnabled,
                    onFood = viewModel::setFoodTreatVisible,
                )
                state.message?.let {
                    Text(
                        it,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.padding(vertical = 4.dp),
                    )
                }
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(top = 8.dp, bottom = 24.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.fillMaxSize(),
                ) {
                    items(
                        items = rows,
                        key = {
                            when (it) {
                                is ShopGridRow.Header -> "h-${it.tier}"
                                is ShopGridRow.Item -> it.item.id
                                is ShopGridRow.RecentTitle -> "recent-title"
                                is ShopGridRow.Recent -> it.id
                            }
                        },
                        span = {
                            when (it) {
                                is ShopGridRow.Item -> GridItemSpan(1)
                                else -> GridItemSpan(2)
                            }
                        },
                    ) { row ->
                        when (row) {
                            is ShopGridRow.Header -> ShopSectionHeader(row.tier)
                            is ShopGridRow.Item -> ShopGridTile(
                                item = row.item,
                                canRedeem = state.rewardsEnabled && state.balance >= row.item.costGx,
                                onRedeem = { viewModel.redeem(row.item) },
                            )
                            is ShopGridRow.RecentTitle -> Text(
                                "Gần đây",
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier.padding(top = 8.dp),
                            )
                            is ShopGridRow.Recent -> Text(
                                row.text,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ShopSettingsBar(
    rewardsEnabled: Boolean,
    foodTreatVisible: Boolean,
    onRewards: (Boolean) -> Unit,
    onFood: (Boolean) -> Unit,
) {
    Column(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 12.dp, vertical = 6.dp),
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text("Bật sổ thưởng", style = MaterialTheme.typography.titleMedium)
            Switch(checked = rewardsEnabled, onCheckedChange = onRewards)
        }
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text("Hiện đồ ăn/uống", style = MaterialTheme.typography.bodyLarge)
            Switch(checked = foodTreatVisible, onCheckedChange = onFood)
        }
    }
}

@Composable
private fun ShopSectionHeader(tier: RewardTier) {
    Column(Modifier.padding(top = 6.dp, bottom = 2.dp)) {
        Text(
            tierLabel(tier),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
        )
        if (tier == RewardTier.FOOD_TREAT) {
            Text(
                "PH quyết định — không khuyến khích ép ăn.",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.error,
            )
        }
    }
}

/** Ô hàng kiểu game shop: ảnh lớn + giá GX + nút Đổi. */
@Composable
private fun ShopGridTile(
    item: RewardCatalogItem,
    canRedeem: Boolean,
    onRedeem: () -> Unit,
) {
    val shape = RoundedCornerShape(16.dp)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .background(MaterialTheme.colorScheme.surface)
            .border(1.5.dp, TileBorder, shape)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFF0EBE3)),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.title,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(6.dp),
                contentScale = ContentScale.Fit,
            )
            if (item.caution) {
                Text(
                    "PH chọn",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(4.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.9f))
                        .padding(horizontal = 6.dp, vertical = 2.dp),
                )
            }
        }
        Text(
            item.title,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.SemiBold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
        )
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(999.dp))
                .background(PriceBg)
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_gau_xu),
                contentDescription = null,
                modifier = Modifier
                    .size(16.dp)
                    .clip(RoundedCornerShape(99.dp)),
                contentScale = ContentScale.Crop,
            )
            Text(
                "${item.costGx}",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                color = PriceGold,
            )
        }
        Button(
            onClick = onRedeem,
            enabled = canRedeem,
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 6.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
            ),
            shape = RoundedCornerShape(10.dp),
        ) {
            Text(
                if (canRedeem) "Đổi" else "Chưa đủ",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}
