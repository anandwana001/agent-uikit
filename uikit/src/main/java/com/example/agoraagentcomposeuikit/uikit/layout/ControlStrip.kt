package com.example.agoraagentcomposeuikit.uikit.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CallEnd
import androidx.compose.material.icons.outlined.Mic
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Videocam
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.agoraagentcomposeuikit.uikit.primitives.AgentButton
import com.example.agoraagentcomposeuikit.uikit.primitives.AgentButtonVariant
import com.example.agoraagentcomposeuikit.uikit.primitives.AgentChip
import com.example.agoraagentcomposeuikit.uikit.primitives.AgentIconButton

@Composable
fun ControlStrip(modifier: Modifier = Modifier) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically) {
            AgentIconButton(icon = Icons.Outlined.Mic, contentDescription = "Mic")
            AgentIconButton(icon = Icons.Outlined.Videocam, contentDescription = "Camera")
            AgentIconButton(icon = Icons.Outlined.Settings, contentDescription = "Settings", filled = false)
            AgentIconButton(icon = Icons.Outlined.CallEnd, contentDescription = "End", filled = false)
        }
        AgentButton(text = "Join", variant = AgentButtonVariant.Primary)
        AgentButton(text = "Preview", variant = AgentButtonVariant.Secondary)
        AgentChip(text = "Voice", selected = true)
        AgentChip(text = "Video")
        AgentChip(text = "Transcript", selected = true)
    }
}
