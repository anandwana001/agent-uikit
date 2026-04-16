package com.example.agoraagentcomposeuikit.uikit.video

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Videocam
import androidx.compose.material.icons.outlined.VideocamOff
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.agoraagentcomposeuikit.ui.theme.LocalAgentUIKitPalette

@Composable
fun LocalVideoPreview(
    modifier: Modifier = Modifier,
    showLabel: Boolean = false,
    label: String = "You",
    isVideoMuted: Boolean = false,
    placeholderText: String = "Camera off"
) {
    val palette = LocalAgentUIKitPalette.current
    Surface(
        modifier = modifier,
        color = palette.videoTile,
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, palette.borderStrong)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = if (isVideoMuted) Icons.Outlined.VideocamOff else Icons.Outlined.Videocam,
                    contentDescription = null
                )
                Text(
                    text = placeholderText,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            if (showLabel) {
                Text(
                    text = label,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(12.dp)
                        .background(palette.videoTileOverlay, RoundedCornerShape(999.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}
