package com.example.agoraagentcomposeuikit.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class AgentUIKitPalette(
    val cardLayer1: Color,
    val cardLayer2: Color,
    val cardLayer3: Color,
    val borderSubtle: Color,
    val borderStrong: Color,
    val textHigh: Color,
    val textMedium: Color,
    val textLow: Color,
    val primaryBlue: Color,
    val success: Color,
    val warning: Color,
    val error: Color,
    val videoTile: Color,
    val videoTileOverlay: Color,
    val videoTileAvatar: Color
)

val LocalAgentUIKitPalette = staticCompositionLocalOf {
    AgentUIKitPalette(
        cardLayer1 = CardLayer1,
        cardLayer2 = CardLayer2,
        cardLayer3 = CardLayer3,
        borderSubtle = BorderSubtle,
        borderStrong = BorderStrong,
        textHigh = TextHigh,
        textMedium = TextMedium,
        textLow = TextLow,
        primaryBlue = PrimaryBlue,
        success = Success,
        warning = Warning,
        error = Error,
        videoTile = VideoTile,
        videoTileOverlay = VideoTileOverlay,
        videoTileAvatar = VideoTileAvatar
    )
}

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryBlue,
    onPrimary = TextHigh,
    secondary = SecondaryAction,
    onSecondary = TextHigh,
    background = Background,
    onBackground = TextHigh,
    surface = Surface,
    onSurface = TextHigh,
    surfaceVariant = CardLayer1,
    onSurfaceVariant = TextMedium,
    outline = BorderSubtle
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryBlue,
    onPrimary = TextHigh,
    secondary = SecondaryAction,
    onSecondary = TextHigh,
    background = Background,
    onBackground = TextHigh,
    surface = Surface,
    onSurface = TextHigh,
    surfaceVariant = CardLayer1,
    onSurfaceVariant = TextMedium,
    outline = BorderSubtle
)

@Composable
fun AgoraAgentComposeUIKitTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val scheme = if (darkTheme) DarkColorScheme else LightColorScheme
    MaterialTheme(
        colorScheme = scheme,
        typography = Typography
    ) {
        CompositionLocalProvider(
            LocalAgentUIKitPalette provides AgentUIKitPalette(
                cardLayer1 = CardLayer1,
                cardLayer2 = CardLayer2,
                cardLayer3 = CardLayer3,
                borderSubtle = BorderSubtle,
                borderStrong = BorderStrong,
                textHigh = TextHigh,
                textMedium = TextMedium,
                textLow = TextLow,
                primaryBlue = PrimaryBlue,
                success = Success,
                warning = Warning,
                error = Error,
                videoTile = VideoTile,
                videoTileOverlay = VideoTileOverlay,
                videoTileAvatar = VideoTileAvatar
            ),
            content = content
        )
    }
}
