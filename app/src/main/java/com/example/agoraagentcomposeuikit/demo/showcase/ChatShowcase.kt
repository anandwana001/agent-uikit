package com.example.agoraagentcomposeuikit.demo.showcase

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.agoraagentcomposeuikit.uikit.chat.Conversation
import com.example.agoraagentcomposeuikit.uikit.chat.ConversationContent
import com.example.agoraagentcomposeuikit.uikit.chat.ConversationEmptyState
import com.example.agoraagentcomposeuikit.uikit.chat.ConversationScrollButton
import com.example.agoraagentcomposeuikit.uikit.chat.ConvoTextStream
import com.example.agoraagentcomposeuikit.uikit.chat.Message
import com.example.agoraagentcomposeuikit.uikit.chat.MessageContent
import com.example.agoraagentcomposeuikit.uikit.chat.Response
import com.example.agoraagentcomposeuikit.uikit.models.ConversationMessage
import com.example.agoraagentcomposeuikit.uikit.models.MessageSender
import com.example.agoraagentcomposeuikit.uikit.models.MessageStatus
import com.example.agoraagentcomposeuikit.uikit.models.StreamMessageItem

@Composable
fun ChatShowcase() {
    var activeDemo by remember { mutableStateOf("conversation") }

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        ComponentPicker(
            options = listOf(
                ShowcaseOption("conversation", "Conversation"),
                ShowcaseOption("building-blocks", "Building Blocks"),
                ShowcaseOption("transcript", "Transcript")
            ),
            selectedId = activeDemo,
            onSelect = { activeDemo = it }
        )
        when (activeDemo) {
            "conversation" -> ConversationDemo()
            "building-blocks" -> BuildingBlocksDemo()
            else -> TranscriptDemo()
        }
    }
}

@Composable
private fun ConversationDemo() {
    val messages = remember {
        mutableStateListOf(
            ConversationMessage(
                id = "1",
                sender = MessageSender.Assistant,
                author = "Agent",
                text = "Welcome back. What do you want to work on today?"
            )
        )
    }
    var replyCount by remember { mutableIntStateOf(1) }

    DemoSection(
        title = "Conversation",
        description = "Add user and assistant turns to see the message layout update live."
    ) {
        Conversation(messages = messages)
        ControlButtons(
            "Add User" to {
                messages += ConversationMessage(
                    id = "user-${messages.size}",
                    sender = MessageSender.User,
                    author = "You",
                    text = "Show me a more concise version."
                )
            },
            "Add Agent" to {
                replyCount += 1
                messages += ConversationMessage(
                    id = "agent-${messages.size}",
                    sender = MessageSender.Assistant,
                    author = "Agent",
                    text = "Revision $replyCount is ready with tighter wording and clearer action items."
                )
            },
            "Clear" to { messages.clear() }
        )
        if (messages.isEmpty()) {
            ConversationEmptyState()
        }
    }
}

@Composable
private fun BuildingBlocksDemo() {
    DemoSection(
        title = "Message Building Blocks",
        description = "This is the lower-level composition the library uses under the hood."
    ) {
        ConversationContent {
            Message(from = MessageSender.Assistant, name = "Agent") {
                MessageContent {
                    Response(text = "This response is composed with `Message` + `MessageContent` + `Response`.")
                }
            }
            Message(from = MessageSender.User, name = "You") {
                MessageContent(userMessage = true) {
                    Text(
                        text = "The lower-level pieces are easier to understand in isolation.",
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
        ConversationScrollButton()
    }
}

@Composable
private fun TranscriptDemo() {
    var currentDraft by remember {
        mutableStateOf(
            StreamMessageItem(
                turnId = "draft",
                uid = 0,
                text = "Drafting a concise summary with next steps...",
                status = MessageStatus.InProgress
            )
        )
    }

    DemoSection(
        title = "Realtime Transcript",
        description = "Toggle whether the assistant is still speaking or has completed the current turn."
    ) {
        ConvoTextStream(
            modifier = Modifier.fillMaxWidth(),
            messageList = listOf(
                StreamMessageItem("1", 1001, "Summarize the call and flag blockers.", MessageStatus.Completed),
                StreamMessageItem("2", 0, "I found two blockers so far.", MessageStatus.Completed)
            ),
            currentInProgressMessage = currentDraft,
            agentUid = "0"
        )
        ControlButtons(
            "Keep Streaming" to {
                currentDraft = currentDraft.copy(
                    text = "I am still collecting decisions, blockers, and owners from the transcript...",
                    status = MessageStatus.InProgress
                )
            },
            "Finish Reply" to {
                currentDraft = currentDraft.copy(
                    text = "Summary ready: analytics access is blocked and speaker timing is still unresolved.",
                    status = MessageStatus.Completed
                )
            }
        )
    }
}
