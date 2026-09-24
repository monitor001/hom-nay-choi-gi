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

private val Forest = Color(0xFF2F6B4F)
private val SoftSand = Color(0xFFF7F1E8)
private val Ink = Color(0xFF1C2A22)
private val Accent = Color(0xFFD97706)

private val LightColors = lightColorScheme(
    primary = Forest,
    onPrimary = Color.White,
    secondary = Accent,
    onSecondary = Color.White,
    background = SoftSand,
    onBackground = Ink,
    surface = Color(0xFFFFFBF6),
    onSurface = Ink,
)

private fun scaledTypography(fontScale: Float): Typography {
    val base = Typography()
    fun TextStyle.scaled() = copy(fontSize = fontSize * fontScale, lineHeight = lineHeight * fontScale)
    return Typography(
        displayLarge = base.displayLarge.scaled().copy(fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold),
        headlineMedium = base.headlineMedium.scaled().copy(fontFamily = FontFamily.Serif, fontWeight = FontWeight.SemiBold),
        titleLarge = base.titleLarge.scaled(),
        titleMedium = base.titleMedium.scaled(),
        bodyLarge = base.bodyLarge.scaled().copy(fontSize = 18.sp * fontScale, lineHeight = 26.sp * fontScale),
        bodyMedium = base.bodyMedium.scaled(),
        labelLarge = base.labelLarge.scaled(),
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
