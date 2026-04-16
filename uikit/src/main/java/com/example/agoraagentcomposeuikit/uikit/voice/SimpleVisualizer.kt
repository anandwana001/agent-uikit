package com.example.agoraagentcomposeuikit.uikit.voice

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.agoraagentcomposeuikit.ui.theme.LocalAgentUIKitPalette

@Composable
fun SimpleVisualizer(
    data: List<Float>,
    modifier: Modifier = Modifier,
    activeColor: Color = LocalAgentUIKitPalette.current.textHigh,
    inactiveColor: Color = LocalAgentUIKitPalette.current.textLow,
    barWidth: Dp = 3.dp,
    barHeight: Dp = 14.dp,
    gap: Dp = 2.dp
) {
    Row(
        modifier = modifier.height(barHeight),
        horizontalArrangement = Arrangement.spacedBy(gap)
    ) {
        data.forEach { sample ->
            Box(
                modifier = Modifier
                    .width(barWidth)
                    .height(barHeight)
                    .background(
                        color = if (sample > 0f) activeColor else inactiveColor,
                        shape = RoundedCornerShape(999.dp)
                    )
            )
        }
    }
}
