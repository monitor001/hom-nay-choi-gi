package com.gaucon.feature.library

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.gaucon.core.designsystem.FullscreenImageViewer
import com.gaucon.core.designsystem.ZoomableAssetImage
import com.gaucon.core.designsystem.rememberAssetImageBitmap

@Composable
fun ResourceAssetImage(
    assetPath: String?,
    contentDescription: String?,
    size: Dp = 72.dp,
    modifier: Modifier = Modifier,
    openFullscreenOnClick: Boolean = true,
) {
    val bitmap = rememberAssetImageBitmap(assetPath) ?: return
    var fullscreen by remember { mutableStateOf(false) }
    androidx.compose.foundation.Image(
        bitmap = bitmap,
        contentDescription = contentDescription,
        modifier = modifier
            .size(size)
            .clip(RoundedCornerShape(12.dp))
            .then(
                if (openFullscreenOnClick) {
                    Modifier.clickable { fullscreen = true }
                } else {
                    Modifier
                },
            ),
        contentScale = ContentScale.Crop,
    )
    if (fullscreen) {
        FullscreenImageViewer(
            image = bitmap,
            title = contentDescription,
            onDismiss = { fullscreen = false },
        )
    }
}

/** Tranh lớn trên màn chi tiết — bấm để xem full cho bé. */
@Composable
fun ResourceHeroImage(
    assetPath: String?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
) {
    ZoomableAssetImage(
        assetPath = assetPath,
        contentDescription = contentDescription,
        contentScale = ContentScale.Fit,
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clip(RoundedCornerShape(20.dp)),
    )
}
