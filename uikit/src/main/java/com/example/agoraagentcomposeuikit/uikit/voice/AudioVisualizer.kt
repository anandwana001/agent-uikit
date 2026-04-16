package com.example.agoraagentcomposeuikit.uikit.voice

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AudioVisualizer(
    modifier: Modifier = Modifier,
    gradientColors: List<Color> = listOf(
        Color(0xFFA0FAFF),
        Color(0xFFFCF9F8),
        Color(0xFFC46FFB)
    ),
    sampleData: List<Float> = listOf(0.22f, 0.36f, 0.5f, 0.68f, 0.84f, 0.68f, 0.5f, 0.36f, 0.22f)
) {
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(96.dp)
    ) {
        val barWidth = size.width / (sampleData.size * 1.8f)
        val gap = barWidth * 0.8f
        sampleData.forEachIndexed { index, sample ->
            val barHeight = sample * size.height
            drawRoundRect(
                brush = Brush.verticalGradient(gradientColors),
                topLeft = Offset(index * (barWidth + gap), size.height - barHeight),
                size = Size(barWidth, barHeight),
                cornerRadius = CornerRadius(barWidth / 2f, barWidth / 2f)
            )
        }
    }
}
