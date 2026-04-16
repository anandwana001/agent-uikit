package com.example.agoraagentcomposeuikit.uikit.chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.agoraagentcomposeuikit.uikit.models.MessageStatus
import com.example.agoraagentcomposeuikit.uikit.models.StreamMessageItem
import com.example.agoraagentcomposeuikit.uikit.primitives.AgentCard

@Composable
fun ConvoTextStream(
    messageList: List<StreamMessageItem>,
    currentInProgressMessage: StreamMessageItem? = null,
    agentUid: String? = null,
    modifier: Modifier = Modifier
) {
    val allMessages = buildList {
        addAll(messageList)
        if (currentInProgressMessage != null &&
            currentInProgressMessage.status == MessageStatus.InProgress &&
            currentInProgressMessage.text.isNotBlank()
        ) {
            add(currentInProgressMessage)
        }
    }

    AgentCard(
        modifier = modifier,
        title = "Transcription",
        subtitle = if (agentUid != null) "Agent UID: $agentUid" else "Realtime transcript"
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 180.dp, max = 420.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(allMessages, key = { "${it.turnId}-${it.uid}-${it.status}" }) { item ->
                val isAgent = item.uid == 0 || item.uid.toString() == agentUid
                Column {
                    Text(
                        text = if (isAgent) "AI" else "User",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    MessageContent(userMessage = !isAgent) {
                        Text(
                            text = item.text,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}
