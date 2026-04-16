package com.example.agoraagentcomposeuikit.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.agoraagentcomposeuikit.ui.theme.Background
import com.example.agoraagentcomposeuikit.uikit.chat.Conversation
import com.example.agoraagentcomposeuikit.uikit.chat.ConvoTextStream
import com.example.agoraagentcomposeuikit.uikit.layout.ControlStrip
import com.example.agoraagentcomposeuikit.uikit.layout.MobileTabs
import com.example.agoraagentcomposeuikit.uikit.layout.VideoGrid
import com.example.agoraagentcomposeuikit.uikit.models.CameraDevice
import com.example.agoraagentcomposeuikit.uikit.models.AgentVisualizerSize
import com.example.agoraagentcomposeuikit.uikit.models.AgentVisualizerState
import com.example.agoraagentcomposeuikit.uikit.models.AvatarVideoState
import com.example.agoraagentcomposeuikit.uikit.models.ConversationMessage
import com.example.agoraagentcomposeuikit.uikit.models.MessageSender
import com.example.agoraagentcomposeuikit.uikit.models.MessageStatus
import com.example.agoraagentcomposeuikit.uikit.models.MicButtonState
import com.example.agoraagentcomposeuikit.uikit.models.MobileTab
import com.example.agoraagentcomposeuikit.uikit.models.SettingOption
import com.example.agoraagentcomposeuikit.uikit.models.ShenMeasurementResults
import com.example.agoraagentcomposeuikit.uikit.models.ShenState
import com.example.agoraagentcomposeuikit.uikit.models.StreamMessageItem
import com.example.agoraagentcomposeuikit.uikit.branding.AgoraLogo
import com.example.agoraagentcomposeuikit.uikit.primitives.AgentButton
import com.example.agoraagentcomposeuikit.uikit.primitives.AgentButtonVariant
import com.example.agoraagentcomposeuikit.uikit.primitives.AgentCard
import com.example.agoraagentcomposeuikit.uikit.primitives.Command
import com.example.agoraagentcomposeuikit.uikit.primitives.ValuePicker
import com.example.agoraagentcomposeuikit.uikit.session.SessionPanel
import com.example.agoraagentcomposeuikit.uikit.settings.SettingsDialog
import com.example.agoraagentcomposeuikit.uikit.settings.AgentSettingsPanel
import com.example.agoraagentcomposeuikit.uikit.shen.ShenPanel
import com.example.agoraagentcomposeuikit.uikit.video.CameraSelector
import com.example.agoraagentcomposeuikit.uikit.video.LocalVideoPreview
import com.example.agoraagentcomposeuikit.uikit.video.AvatarVideoDisplay
import com.example.agoraagentcomposeuikit.uikit.voice.AgentVisualizer
import com.example.agoraagentcomposeuikit.uikit.voice.AudioVisualizer
import com.example.agoraagentcomposeuikit.uikit.voice.LiveWaveform
import com.example.agoraagentcomposeuikit.uikit.voice.MicButton
import com.example.agoraagentcomposeuikit.uikit.voice.MicButtonWithVisualizer
import com.example.agoraagentcomposeuikit.uikit.voice.MicSelector

@Composable
fun AgentUIKitShowcase() {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(WindowInsets.safeDrawing.asPaddingValues())
    ) {
        val compact = maxWidth < 700.dp
        if (compact) {
            MobileShowcase()
        } else {
            TabletDesktopShowcase()
        }
    }
}

@Composable
private fun MobileShowcase() {
    var activeTab by remember { mutableStateOf("live") }
    val tabs = listOf(
        MobileTab("live", "Live"),
        MobileTab("chat", "Chat"),
        MobileTab("settings", "Settings"),
        MobileTab("session", "Session")
    )
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item { ShowcaseHeader(compact = true) }
        item {
            MobileTabs(
                tabs = tabs,
                activeTabId = activeTab,
                onTabSelected = { activeTab = it }
            )
        }
        item {
            when (activeTab) {
                "live" -> MobileLivePane()
                "chat" -> AgentCard(title = "Conversation") { Conversation(messages = sampleMessages()) }
                "settings" -> SettingsPane()
                else -> SessionPane()
            }
        }
    }
}

@Composable
private fun TabletDesktopShowcase() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item { ShowcaseHeader(compact = false) }
        item {
            VideoGrid(
                chat = { Conversation(messages = sampleMessages()) },
                avatar = {
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        AvatarVideoDisplay(
                            state = AvatarVideoState.Connected,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(320.dp)
                        )
                        AgentVisualizer(
                            state = AgentVisualizerState.Listening,
                            visualizerSize = AgentVisualizerSize.Small
                        )
                    }
                },
                localVideo = { LocalPreviewPane() },
                controls = { ControlStrip() }
            )
        }
        item { VoicePane() }
        item { VoiceAdvancedPane() }
        item { PrimitivePane() }
        item { VideoAdvancedPane() }
        item { ChatAdvancedPane() }
        item { SettingsPane() }
        item { SessionPane() }
        item { BrandingAndShenPane() }
    }
}

@Composable
private fun ShowcaseHeader(compact: Boolean) {
    AgentCard(
        title = "Agora Agent Compose UIKit",
        subtitle = "Dark, token-driven Compose components inspired by the web agent-uikit."
    ) {
        Text(
            text = "This version focuses on reusable library pieces and mobile-friendly composition instead of one oversized demo surface.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        if (!compact) {
            Spacer(Modifier.height(4.dp))
        }
        androidx.compose.foundation.layout.Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            AgentButton(text = "Primary Action")
            AgentButton(text = "Secondary", variant = AgentButtonVariant.Secondary)
        }
    }
}

@Composable
private fun MobileLivePane() {
    AgentCard(title = "Live Session", subtitle = "Phone-first composition with tighter sections.") {
        AvatarVideoDisplay(
            state = AvatarVideoState.Connected,
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
        )
        AgentVisualizer(
            state = AgentVisualizerState.Talking,
            visualizerSize = AgentVisualizerSize.Small
        )
        VoicePane()
        LocalPreviewPane()
        AgentCard(title = "Controls") { ControlStrip() }
    }
}

@Composable
private fun VoicePane() {
    AgentCard(
        title = "Voice Components",
        subtitle = "Mic states and animated agent status."
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            MicButton(state = MicButtonState.Idle, audioData = listOf(0f, 0f, 0f, 0f, 0f))
            MicButton(state = MicButtonState.Listening, audioData = listOf(1f, 1f, 0f, 1f, 0f))
            MicButton(state = MicButtonState.Processing, audioData = listOf(1f, 1f, 1f, 0f, 1f))
            MicButton(state = MicButtonState.Error)
        }
    }
}

@Composable
private fun LocalPreviewPane() {
    AgentCard(title = "Local Preview") {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .background(MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Text(
                text = "Camera preview slot",
                modifier = Modifier.padding(12.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
private fun SettingsPane() {
    var dialogOpen by remember { mutableStateOf(false) }
    val mics = listOf(
        SettingOption("", "System Default"),
        SettingOption("usb", "USB Podcast Mic"),
        SettingOption("bt", "Bluetooth Headset")
    )
    val languages = listOf(
        SettingOption("en-US", "English (US)"),
        SettingOption("hi-IN", "Hindi"),
        SettingOption("ja-JP", "Japanese")
    )
    AgentSettingsPanel(
        microphoneOptions = mics,
        languageOptions = languages,
        selectedMicId = "usb",
        selectedLanguageId = "en-US",
        enableTurnDetection = true,
        prompt = "You are a helpful real-time voice assistant. Keep responses short, clear, and action-oriented.",
        greeting = "Hi, how can I help today?"
    )
    AgentCard(title = "Settings Dialog") {
        AgentButton(text = "Open Dialog", onClick = { dialogOpen = true })
    }
    SettingsDialog(
        open = dialogOpen,
        onOpenChange = { dialogOpen = it },
        microphoneOptions = mics,
        languageOptions = languages,
        selectedMicId = "usb",
        selectedLanguageId = "en-US",
        enableTurnDetection = true,
        prompt = "You are a helpful real-time voice assistant. Keep responses short, clear, and action-oriented.",
        greeting = "Hi, how can I help today?"
    )
}

@Composable
private fun SessionPane() {
    SessionPanel(
        agentId = "agent_01JZ8YB9Q8N4B6C2XK5A",
        payloadPreview = """{"channel":"demo-room","uid":"local-user","voice":"alloy"}"""
    )
}

private fun sampleMessages(): List<ConversationMessage> = listOf(
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

@Composable
private fun VoiceAdvancedPane() {
    AgentCard(title = "Advanced Voice", subtitle = "Visualizer, waveform, selector, and combined mic control.") {
        AudioVisualizer()
        LiveWaveform(active = true)
        MicSelector(
            devices = listOf(
                SettingOption("default", "System Default"),
                SettingOption("usb", "USB Podcast Mic")
            ),
            value = "usb",
            muted = false,
            state = MicButtonState.Listening
        )
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            MicButtonWithVisualizer(isEnabled = true)
            MicButtonWithVisualizer(isEnabled = false)
        }
    }
}

@Composable
private fun PrimitivePane() {
    AgentCard(title = "Primitives", subtitle = "Picker and command surface.") {
        ValuePicker(
            items = listOf(
                SettingOption("one", "First item"),
                SettingOption("two", "Second item")
            ),
            value = "one",
            label = "Theme"
        )
        Command(
            items = listOf(
                SettingOption("join", "Join room"),
                SettingOption("leave", "Leave room"),
                SettingOption("mute", "Mute mic")
            )
        )
    }
}

@Composable
private fun VideoAdvancedPane() {
    AgentCard(title = "Advanced Video", subtitle = "Local preview and camera selection.") {
        LocalVideoPreview(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            showLabel = true
        )
        CameraSelector(
            devices = listOf(
                CameraDevice("front", "Front Camera"),
                CameraDevice("rear", "Rear Camera")
            ),
            value = "front"
        )
    }
}

@Composable
private fun ChatAdvancedPane() {
    AgentCard(title = "Streaming Transcript", subtitle = "ConvoTextStream parity layer.") {
        ConvoTextStream(
            messageList = listOf(
                StreamMessageItem("1", 0, "Hello, how can I help?", MessageStatus.Completed),
                StreamMessageItem("2", 1001, "Summarize the call please.", MessageStatus.Completed)
            ),
            currentInProgressMessage = StreamMessageItem(
                turnId = "3",
                uid = 0,
                text = "I am drafting a concise summary with blockers...",
                status = MessageStatus.InProgress
            ),
            agentUid = "0"
        )
    }
}

@Composable
private fun BrandingAndShenPane() {
    AgentCard(title = "Branding & Biomarkers", subtitle = "Agora logo and Shen panel.") {
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            AgoraLogo(modifier = Modifier.height(32.dp).fillMaxWidth(0.2f))
            Text("Agora UIKit", style = MaterialTheme.typography.titleMedium)
        }
        ShenPanel(
            shenState = ShenState(
                sdkLoaded = true,
                progress = 82,
                heartRate = 72.0,
                hrvSdnn = 42.0,
                stressIndex = 18.0,
                breathingRate = 14.0,
                results = ShenMeasurementResults(
                    ageYears = 29.0,
                    systolicBp = 118.0,
                    diastolicBp = 76.0,
                    signalQuality = 0.86
                )
            ),
            isConnected = true
        )
    }
}
