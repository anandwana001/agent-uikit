package com.example.agoraagentcomposeuikit.uikit.video

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.agoraagentcomposeuikit.ui.theme.LocalAgentUIKitPalette
import com.example.agoraagentcomposeuikit.uikit.models.AvatarVideoState

@Composable
fun AvatarVideoDisplay(
    state: AvatarVideoState,
    modifier: Modifier = Modifier,
    title: String = "Agent Video"
) {
    val palette = LocalAgentUIKitPalette.current
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(palette.videoTile)
            .padding(14.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = title, style = MaterialTheme.typography.labelLarge)
                Text(
                    text = when (state) {
                        AvatarVideoState.Connected -> "Live"
                        AvatarVideoState.Loading -> "Connecting"
                        AvatarVideoState.Disconnected -> "Offline"
                    },
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                AgentAvatar(name = "Agora Agent")
            }
        }
    }
}
