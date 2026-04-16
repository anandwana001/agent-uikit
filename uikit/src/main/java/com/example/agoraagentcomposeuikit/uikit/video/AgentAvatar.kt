package com.example.agoraagentcomposeuikit.uikit.video

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.agoraagentcomposeuikit.ui.theme.LocalAgentUIKitPalette

@Composable
fun AgentAvatar(
    name: String,
    modifier: Modifier = Modifier
) {
    val palette = LocalAgentUIKitPalette.current
    Box(
        modifier = modifier
            .size(56.dp)
            .clip(CircleShape)
            .background(palette.videoTileAvatar),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = name.split(" ").take(2).mapNotNull { it.firstOrNull()?.toString() }.joinToString(""),
            style = MaterialTheme.typography.labelLarge,
            color = palette.cardLayer1
        )
    }
}
