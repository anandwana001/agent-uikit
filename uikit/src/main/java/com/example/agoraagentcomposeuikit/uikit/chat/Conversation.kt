package com.example.agoraagentcomposeuikit.uikit.chat

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.ArrowDownward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.agoraagentcomposeuikit.ui.theme.LocalAgentUIKitPalette
import com.example.agoraagentcomposeuikit.uikit.models.ConversationMessage
import com.example.agoraagentcomposeuikit.uikit.models.MessageSender
import com.example.agoraagentcomposeuikit.uikit.primitives.AgentButton
import com.example.agoraagentcomposeuikit.uikit.primitives.AgentButtonVariant

@Composable
fun Conversation(
    messages: List<ConversationMessage>,
    modifier: Modifier = Modifier
) {
    ConversationContent(modifier = modifier) {
        if (messages.isEmpty()) {
            ConversationEmptyState()
        } else {
            messages.forEach { message ->
                Message(
                    from = message.sender,
                    name = message.author
                ) {
                    MessageContent(userMessage = message.sender == MessageSender.User) {
                        Text(
                            text = message.text,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ConversationContent(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 220.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        content = content
    )
}

@Composable
fun ConversationEmptyState(
    modifier: Modifier = Modifier,
    title: String = "No messages yet",
    description: String = "Start a conversation to see messages here"
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = Icons.Outlined.ChatBubbleOutline,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.height(8.dp))
            Text(text = title, style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(4.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun ConversationScrollButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    androidx.compose.foundation.layout.Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Outlined.ArrowDownward, contentDescription = null)
        Spacer(Modifier.width(6.dp))
        AgentButton(
            text = "Latest",
            variant = AgentButtonVariant.Secondary,
            onClick = onClick
        )
    }
}

@Composable
fun Message(
    from: MessageSender,
    modifier: Modifier = Modifier,
    name: String? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val palette = LocalAgentUIKitPalette.current
    val user = from == MessageSender.User
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = if (user) Alignment.End else Alignment.Start
    ) {
        name?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.labelSmall,
                color = palette.textLow
            )
            Spacer(Modifier.height(4.dp))
        }
        Column(
            horizontalAlignment = if (user) Alignment.End else Alignment.Start,
            content = content
        )
    }
}

@Composable
fun MessageContent(
    modifier: Modifier = Modifier,
    userMessage: Boolean = false,
    content: @Composable ColumnScope.() -> Unit
) {
    val palette = LocalAgentUIKitPalette.current
    Surface(
        color = if (userMessage) palette.cardLayer3 else palette.cardLayer2,
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, palette.borderSubtle),
        modifier = modifier.widthIn(max = 320.dp)
    ) {
        Column(content = content)
    }
}
