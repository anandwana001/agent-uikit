package com.example.agoraagentcomposeuikit.uikit.voice

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.agoraagentcomposeuikit.ui.theme.LocalAgentUIKitPalette
import kotlin.math.max

@Composable
fun LiveWaveform(
    active: Boolean = false,
    data: List<Float> = emptyList(),
    modifier: Modifier = Modifier,
    barColor: Color = LocalAgentUIKitPalette.current.primaryBlue
) {
    val samples = if (data.isNotEmpty()) data else List(24) { if (active) (it % 5) / 5f else 0.08f }
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
    ) {
        val barWidth = size.width / (samples.size * 1.5f)
        val gap = barWidth / 2f
        samples.forEachIndexed { index, value ->
            val height = max(4f, value * size.height)
            val left = index * (barWidth + gap)
            drawRoundRect(
                color = barColor,
                topLeft = androidx.compose.ui.geometry.Offset(left, (size.height - height) / 2f),
                size = androidx.compose.ui.geometry.Size(barWidth, height),
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(barWidth / 2f, barWidth / 2f)
            )
        }
    }
}
