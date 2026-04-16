package com.example.agoraagentcomposeuikit.uikit.session

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.agoraagentcomposeuikit.ui.theme.LocalAgentUIKitPalette
import com.example.agoraagentcomposeuikit.uikit.primitives.AgentCard

@Composable
fun SessionPanel(
    agentId: String,
    payloadPreview: String,
    modifier: Modifier = Modifier
) {
    AgentCard(
        modifier = modifier,
        title = "Session Panel",
        subtitle = "Agent id and join payload preview."
    ) {
        SessionValue("Agent ID", agentId)
        SessionValue("Join Request Body", payloadPreview)
    }
}

@Composable
private fun SessionValue(label: String, value: String) {
    val palette = LocalAgentUIKitPalette.current
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(label, style = MaterialTheme.typography.labelLarge)
        Surface(
            color = palette.cardLayer2,
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(12.dp),
                maxLines = 5,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
