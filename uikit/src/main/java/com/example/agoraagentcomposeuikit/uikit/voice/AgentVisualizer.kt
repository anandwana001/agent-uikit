package com.example.agoraagentcomposeuikit.uikit.voice

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.agoraagentcomposeuikit.ui.theme.LocalAgentUIKitPalette
import com.example.agoraagentcomposeuikit.uikit.models.AgentVisualizerSize
import com.example.agoraagentcomposeuikit.uikit.models.AgentVisualizerState
import kotlin.math.max

@Composable
fun AgentVisualizer(
    state: AgentVisualizerState,
    modifier: Modifier = Modifier,
    visualizerSize: AgentVisualizerSize = AgentVisualizerSize.Medium
) {
    val palette = LocalAgentUIKitPalette.current
    val transition = rememberInfiniteTransition(label = "agent-visualizer")
    val pulse by transition.animateFloat(
        initialValue = 0.35f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = when (state) {
                    AgentVisualizerState.Talking -> 650
                    AgentVisualizerState.Listening -> 800
                    AgentVisualizerState.Analyzing -> 1000
                    AgentVisualizerState.Joining -> 1200
                    else -> 1800
                },
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "agent-pulse"
    )
    val accent = when (state) {
        AgentVisualizerState.Listening -> palette.success
        AgentVisualizerState.Analyzing -> palette.warning
        AgentVisualizerState.Talking -> palette.primaryBlue
        AgentVisualizerState.Disconnected -> palette.error
        else -> palette.primaryBlue
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(visualizerSize.diameter)
                .background(palette.cardLayer2, CircleShape)
                .border(1.dp, palette.borderStrong, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.fillMaxSize().padding(18.dp)) {
                val radius = size.minDimension / 2f
                drawCircle(accent.copy(alpha = 0.08f + (pulse * 0.08f)), radius = radius * 0.92f)
                drawCircle(accent.copy(alpha = 0.16f), radius = radius * 0.56f, style = Stroke(width = 10f))
                repeat(4) { index ->
                    val inset = 30f + index * 18f
                    drawArc(
                        color = accent.copy(alpha = max(0.12f, pulse - index * 0.16f)),
                        startAngle = index * 40f,
                        sweepAngle = 140f + pulse * 120f,
                        useCenter = false,
                        topLeft = Offset(inset, inset),
                        size = Size(size.width - inset * 2, size.height - inset * 2),
                        style = Stroke(
                            width = 7f,
                            cap = StrokeCap.Round,
                            pathEffect = PathEffect.cornerPathEffect(16f)
                        )
                    )
                }
            }
            Text(
                text = state.label,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
        }
        Text(
            text = state.helper,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
    }
}

private val AgentVisualizerState.label: String
    get() = when (this) {
        AgentVisualizerState.NotJoined -> "Not joined"
        AgentVisualizerState.Joining -> "Joining"
        AgentVisualizerState.Ambient -> "Ambient"
        AgentVisualizerState.Listening -> "Listening"
        AgentVisualizerState.Analyzing -> "Analyzing"
        AgentVisualizerState.Talking -> "Talking"
        AgentVisualizerState.Disconnected -> "Disconnected"
    }

private val AgentVisualizerState.helper: String
    get() = when (this) {
        AgentVisualizerState.NotJoined -> "Ready for a new session."
        AgentVisualizerState.Joining -> "Connecting to the room."
        AgentVisualizerState.Ambient -> "Waiting for the next turn."
        AgentVisualizerState.Listening -> "Capturing user audio."
        AgentVisualizerState.Analyzing -> "Reasoning over the prompt."
        AgentVisualizerState.Talking -> "Streaming the reply."
        AgentVisualizerState.Disconnected -> "Session ended."
    }
