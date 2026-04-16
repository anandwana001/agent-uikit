package com.example.agoraagentcomposeuikit.uikit.branding

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color

@Composable
fun AgoraLogo(
    modifier: Modifier = Modifier,
    color: Color = Color(0xFF00C2FF)
) {
    Canvas(modifier = modifier) {
        val stroke = size.minDimension * 0.12f
        drawArc(
            color = color,
            startAngle = -40f,
            sweepAngle = 320f,
            useCenter = false,
            topLeft = Offset(stroke, stroke),
            size = Size(size.width - stroke * 2, size.height - stroke * 2),
            style = androidx.compose.ui.graphics.drawscope.Stroke(width = stroke)
        )
        drawCircle(
            color = color,
            radius = size.minDimension * 0.14f,
            center = center
        )
    }
}
