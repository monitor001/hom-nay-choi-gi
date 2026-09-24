package com.gaucon.core.designsystem

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/** Soft teal — parent companion, not purple-on-white. */
private val Teal = Color(0xFF1F6F6A)
private val TealContainer = Color(0xFFD5EDEB)
private val Coral = Color(0xFFD96B4C)
private val Cream = Color(0xFFF6F0E6)
private val Paper = Color(0xFFFFFBF5)
private val Ink = Color(0xFF1A2B28)
private val Muted = Color(0xFF5C6F6B)

private val LightColors = lightColorScheme(
    primary = Teal,
    onPrimary = Color.White,
    primaryContainer = TealContainer,
    onPrimaryContainer = Color(0xFF0D3D3A),
    secondary = Coral,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFF8E0D8),
    onSecondaryContainer = Color(0xFF5C2A1C),
    tertiary = Color(0xFFC4A35A),
    tertiaryContainer = Color(0xFFF3E8C8),
    background = Cream,
    onBackground = Ink,
    surface = Paper,
    onSurface = Ink,
    surfaceVariant = Color(0xFFE8E2D6),
    onSurfaceVariant = Muted,
    outline = Color(0xFFB8B0A2),
)

private fun scaledTypography(fontScale: Float): Typography {
    val base = Typography()
    fun TextStyle.scaled() = copy(fontSize = fontSize * fontScale, lineHeight = lineHeight * fontScale)
    return Typography(
        displayLarge = base.displayLarge.scaled().copy(fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold),
        headlineMedium = base.headlineMedium.scaled().copy(fontFamily = FontFamily.Serif, fontWeight = FontWeight.SemiBold),
        titleLarge = base.titleLarge.scaled().copy(fontWeight = FontWeight.SemiBold),
        titleMedium = base.titleMedium.scaled(),
        bodyLarge = base.bodyLarge.scaled().copy(fontSize = 18.sp * fontScale, lineHeight = 26.sp * fontScale),
        bodyMedium = base.bodyMedium.scaled(),
        labelLarge = base.labelLarge.scaled(),
        labelMedium = base.labelMedium.scaled(),
    )
}

@Composable
fun GauConTheme(
    fontScale: Float = 1.0f,
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = scaledTypography(fontScale),
        content = content,
    )
}
