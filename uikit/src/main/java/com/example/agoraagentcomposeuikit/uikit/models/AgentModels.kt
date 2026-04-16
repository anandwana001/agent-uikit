package com.example.agoraagentcomposeuikit.uikit.models

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class MicButtonState {
    Idle,
    Listening,
    Processing,
    Error
}

enum class AgentVisualizerState {
    NotJoined,
    Joining,
    Ambient,
    Listening,
    Analyzing,
    Talking,
    Disconnected
}

enum class AgentVisualizerSize(val diameter: Dp) {
    Small(128.dp),
    Medium(192.dp),
    Large(256.dp)
}

enum class MessageSender {
    User,
    Assistant
}

enum class AvatarVideoState {
    Connected,
    Loading,
    Disconnected
}

data class ConversationMessage(
    val id: String,
    val sender: MessageSender,
    val author: String,
    val text: String
)

data class SettingOption(
    val id: String,
    val name: String
)

data class MobileTab(
    val id: String,
    val label: String
)

data class CameraDevice(
    val deviceId: String,
    val label: String,
    val groupId: String? = null
)

enum class MessageStatus {
    Completed,
    InProgress,
    Failed
}

data class StreamMessageItem(
    val turnId: String,
    val uid: Int,
    val text: String,
    val status: MessageStatus = MessageStatus.Completed
)

data class ShenMeasurementResults(
    val ageYears: Double? = null,
    val heartRateBpm: Double? = null,
    val hrvSdnnMs: Double? = null,
    val stressIndex: Double? = null,
    val breathingRateBpm: Double? = null,
    val systolicBp: Double? = null,
    val diastolicBp: Double? = null,
    val cardiacWorkload: Double? = null,
    val signalQuality: Double? = null
)

data class ShenState(
    val sdkLoaded: Boolean = false,
    val progress: Int = 0,
    val heartRate: Double? = null,
    val realtimeHr: Double? = null,
    val hrvSdnn: Double? = null,
    val stressIndex: Double? = null,
    val breathingRate: Double? = null,
    val results: ShenMeasurementResults? = null
)
