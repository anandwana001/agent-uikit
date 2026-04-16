package com.example.agoraagentcomposeuikit.uikit.voice

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.agoraagentcomposeuikit.ui.theme.LocalAgentUIKitPalette
import com.example.agoraagentcomposeuikit.uikit.models.MicButtonState

@Composable
fun MicButton(
    state: MicButtonState,
    modifier: Modifier = Modifier,
    audioData: List<Float> = emptyList()
) {
    val palette = LocalAgentUIKitPalette.current
    val background = when (state) {
        MicButtonState.Idle -> palette.cardLayer2
        MicButtonState.Listening -> palette.primaryBlue.copy(alpha = 0.15f)
        MicButtonState.Processing -> palette.cardLayer3
        MicButtonState.Error -> palette.error.copy(alpha = 0.15f)
    }
    val indicator = when (state) {
        MicButtonState.Idle -> palette.textLow
        MicButtonState.Listening -> palette.success
        MicButtonState.Processing -> palette.warning
        MicButtonState.Error -> palette.error
    }
    Surface(
        modifier = modifier,
        color = background,
        shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, palette.borderStrong)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(indicator)
            )
            Text(
                text = when (state) {
                    MicButtonState.Idle -> "Microphone"
                    MicButtonState.Listening -> "Listening"
                    MicButtonState.Processing -> "Processing"
                    MicButtonState.Error -> "Mic error"
                },
                style = MaterialTheme.typography.labelLarge
            )
            if (audioData.isNotEmpty()) {
                SimpleVisualizer(
                    data = audioData,
                    activeColor = Color.White,
                    inactiveColor = palette.textLow
                )
            }
        }
    }
}
