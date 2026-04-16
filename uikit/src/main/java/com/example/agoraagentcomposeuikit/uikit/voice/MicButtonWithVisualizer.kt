package com.example.agoraagentcomposeuikit.uikit.voice

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Mic
import androidx.compose.material.icons.outlined.MicOff
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.agoraagentcomposeuikit.ui.theme.LocalAgentUIKitPalette

@Composable
fun MicButtonWithVisualizer(
    isEnabled: Boolean,
    modifier: Modifier = Modifier,
    sampleData: List<Float> = listOf(0.1f, 0.5f, 0.8f, 0.4f, 0.15f),
    onToggle: () -> Unit = {}
) {
    val palette = LocalAgentUIKitPalette.current
    Surface(
        modifier = modifier,
        onClick = onToggle,
        shape = CircleShape,
        color = palette.cardLayer2,
        border = BorderStroke(2.dp, if (isEnabled) palette.primaryBlue else palette.error)
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .background(palette.cardLayer2),
            contentAlignment = Alignment.Center
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(3.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                sampleData.forEach {
                    Box(
                        modifier = Modifier
                            .width(4.dp)
                            .height((8 + (it * 18)).dp)
                            .background(
                                color = if (isEnabled) palette.primaryBlue else palette.error,
                                shape = CircleShape
                            )
                    )
                }
            }
            Icon(
                imageVector = if (isEnabled) Icons.Outlined.Mic else Icons.Outlined.MicOff,
                contentDescription = null
            )
        }
    }
}
