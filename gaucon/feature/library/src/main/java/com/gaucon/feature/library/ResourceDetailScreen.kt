package com.gaucon.feature.library

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gaucon.core.designsystem.FullscreenImageViewer
import com.gaucon.core.designsystem.rememberAssetImageBitmap

@Composable
fun ResourceDetailScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ResourceDetailViewModel = hiltViewModel(),
) {
    val item by viewModel.item.collectAsStateWithLifecycle()
    val loading by viewModel.loading.collectAsStateWithLifecycle()
    var fullscreen by remember { mutableStateOf(false) }
    val heroBitmap = rememberAssetImageBitmap(item?.imageAssetPath)

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        TextButton(onClick = onBack) { Text("← Tài liệu") }
        when {
            loading -> Text("Đang tải…")
            item == null -> Text("Không tìm thấy tài liệu.")
            else -> {
                val r = item!!
                if (heroBitmap != null) {
                    Column(
                        Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        ResourceHeroImage(
                            assetPath = r.imageAssetPath,
                            contentDescription = r.title,
                        )
                        Text(
                            "Chạm tranh để xem toàn màn hình cho bé",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                        Button(
                            onClick = { fullscreen = true },
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Text(
                                "Xem tranh toàn màn hình",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                    }
                }
                Text(r.title, style = MaterialTheme.typography.headlineMedium)
                Text(r.categoryLabel, style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.secondary)
                r.ageHint?.let { Text(it, style = MaterialTheme.typography.bodyMedium) }
                r.source?.let {
                    Text(
                        "Nguồn: $it",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
                r.howToUse?.let {
                    Spacer(Modifier.height(4.dp))
                    Text("Cách dùng", style = MaterialTheme.typography.titleMedium)
                    Text(it, style = MaterialTheme.typography.bodyLarge)
                }
                if (r.body.isNotBlank()) {
                    Spacer(Modifier.height(4.dp))
                    Text("Nội dung", style = MaterialTheme.typography.titleMedium)
                    Text(r.body, style = MaterialTheme.typography.bodyLarge)
                }
                r.storyPrompt?.let {
                    Spacer(Modifier.height(4.dp))
                    Text("Gợi ý kể chuyện", style = MaterialTheme.typography.titleMedium)
                    Text(it, style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }

    if (fullscreen && heroBitmap != null) {
        FullscreenImageViewer(
            image = heroBitmap,
            title = item?.title,
            onDismiss = { fullscreen = false },
        )
    }
}
