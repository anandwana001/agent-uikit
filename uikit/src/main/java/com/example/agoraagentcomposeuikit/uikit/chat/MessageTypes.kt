package com.example.agoraagentcomposeuikit.uikit.chat

import com.example.agoraagentcomposeuikit.uikit.models.MessageStatus
import com.example.agoraagentcomposeuikit.uikit.models.StreamMessageItem

fun transcriptToMessageList(
    items: List<StreamMessageItem>
): List<StreamMessageItem> = items.map { item ->
    item.copy(
        status = when (item.status) {
            MessageStatus.InProgress -> MessageStatus.InProgress
            MessageStatus.Failed -> MessageStatus.Failed
            MessageStatus.Completed -> MessageStatus.Completed
        }
    )
}
