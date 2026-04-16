package com.example.agoraagentcomposeuikit.demo.showcase

import com.example.agoraagentcomposeuikit.uikit.models.CameraDevice
import com.example.agoraagentcomposeuikit.uikit.models.ConversationMessage
import com.example.agoraagentcomposeuikit.uikit.models.MessageSender
import com.example.agoraagentcomposeuikit.uikit.models.SettingOption

fun micOptions(): List<SettingOption> = listOf(
    SettingOption("default", "System Default"),
    SettingOption("usb", "USB Podcast Mic"),
    SettingOption("bt", "Bluetooth Headset")
)

fun languageOptions(): List<SettingOption> = listOf(
    SettingOption("en-US", "English (US)"),
    SettingOption("hi-IN", "Hindi"),
    SettingOption("ja-JP", "Japanese")
)

fun cameraOptions(): List<CameraDevice> = listOf(
    CameraDevice("front", "Front Camera"),
    CameraDevice("rear", "Rear Camera")
)

fun micLabel(id: String): String = micOptions().firstOrNull { it.id == id }?.name ?: id

fun cameraLabel(id: String): String = cameraOptions().firstOrNull { it.deviceId == id }?.label ?: id

fun samplePrompt(): String =
    "You are a helpful real-time voice assistant. Keep responses short, clear, and action-oriented."

fun sampleConversation(): List<ConversationMessage> = listOf(
    ConversationMessage(
        id = "1",
        sender = MessageSender.Assistant,
        author = "Agent",
        text = "Welcome back. I can summarize the meeting, open tools, or draft the next follow-up."
    ),
    ConversationMessage(
        id = "2",
        sender = MessageSender.User,
        author = "You",
        text = "Draft a concise follow-up and flag any blockers from the transcript."
    ),
    ConversationMessage(
        id = "3",
        sender = MessageSender.Assistant,
        author = "Agent",
        text = "Two blockers surfaced: missing analytics access and unresolved speaker timing. I have a draft ready."
    )
)
