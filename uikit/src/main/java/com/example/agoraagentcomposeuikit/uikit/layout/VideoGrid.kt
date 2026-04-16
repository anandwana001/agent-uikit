package com.example.agoraagentcomposeuikit.uikit.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.agoraagentcomposeuikit.uikit.primitives.AgentCard

@Composable
fun VideoGrid(
    chat: @Composable () -> Unit,
    avatar: @Composable () -> Unit,
    localVideo: @Composable () -> Unit,
    controls: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(modifier = modifier.fillMaxWidth()) {
        if (maxWidth < 840.dp) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                AgentCard(title = "Agent") { avatar() }
                AgentCard(title = "Chat") { chat() }
                AgentCard(title = "Local Video") { localVideo() }
                AgentCard(title = "Controls") { controls() }
            }
        } else {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Column(
                    modifier = Modifier.weight(0.4f),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    AgentCard(title = "Chat") { chat() }
                    AgentCard(title = "Local Video") { localVideo() }
                }
                Column(
                    modifier = Modifier.weight(0.6f),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    AgentCard(title = "Agent") { avatar() }
                    AgentCard(title = "Controls") { controls() }
                }
            }
        }
    }
}

@Composable
fun VideoGridWithControls(
    chat: @Composable () -> Unit,
    avatar: @Composable () -> Unit,
    localVideo: @Composable () -> Unit,
    controls: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(modifier = modifier.fillMaxWidth()) {
        if (maxWidth < 840.dp) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                AgentCard(title = "Chat") { chat() }
                AgentCard(title = "Agent") { avatar() }
                AgentCard(title = "Local Video") { localVideo() }
                AgentCard(title = "Controls") { controls() }
            }
        } else {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    AgentCard(modifier = Modifier.weight(1f), title = "Chat") { chat() }
                    AgentCard(modifier = Modifier.weight(1f), title = "Agent") { avatar() }
                }
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    AgentCard(modifier = Modifier.weight(1f), title = "Local Video") { localVideo() }
                    AgentCard(modifier = Modifier.weight(1f), title = "Controls") { controls() }
                }
            }
        }
    }
}
