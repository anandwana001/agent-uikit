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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.agoraagentcomposeuikit.ui.theme.Background
import com.example.agoraagentcomposeuikit.uikit.branding.AgoraLogo
import com.example.agoraagentcomposeuikit.uikit.chat.Conversation
import com.example.agoraagentcomposeuikit.uikit.chat.ConversationContent
import com.example.agoraagentcomposeuikit.uikit.chat.ConversationEmptyState
import com.example.agoraagentcomposeuikit.uikit.chat.ConversationScrollButton
import com.example.agoraagentcomposeuikit.uikit.chat.ConvoTextStream
import com.example.agoraagentcomposeuikit.uikit.chat.Message
import com.example.agoraagentcomposeuikit.uikit.chat.MessageContent
import com.example.agoraagentcomposeuikit.uikit.chat.Response
import com.example.agoraagentcomposeuikit.uikit.layout.ControlStrip
import com.example.agoraagentcomposeuikit.uikit.layout.MobileTabs
import com.example.agoraagentcomposeuikit.uikit.layout.VideoGrid
import com.example.agoraagentcomposeuikit.uikit.layout.VideoGridWithControls
import com.example.agoraagentcomposeuikit.uikit.models.AgentVisualizerSize
import com.example.agoraagentcomposeuikit.uikit.models.AgentVisualizerState
import com.example.agoraagentcomposeuikit.uikit.models.AvatarVideoState
import com.example.agoraagentcomposeuikit.uikit.models.CameraDevice
import com.example.agoraagentcomposeuikit.uikit.models.ConversationMessage
import com.example.agoraagentcomposeuikit.uikit.models.MessageSender
import com.example.agoraagentcomposeuikit.uikit.models.MessageStatus
import com.example.agoraagentcomposeuikit.uikit.models.MicButtonState
import com.example.agoraagentcomposeuikit.uikit.models.MobileTab
import com.example.agoraagentcomposeuikit.uikit.models.SettingOption
import com.example.agoraagentcomposeuikit.uikit.models.ShenMeasurementResults
import com.example.agoraagentcomposeuikit.uikit.models.ShenState
import com.example.agoraagentcomposeuikit.uikit.models.StreamMessageItem
import com.example.agoraagentcomposeuikit.uikit.primitives.AgentButton
import com.example.agoraagentcomposeuikit.uikit.primitives.AgentButtonVariant
import com.example.agoraagentcomposeuikit.uikit.primitives.AgentCard
import com.example.agoraagentcomposeuikit.uikit.primitives.AgentChip
import com.example.agoraagentcomposeuikit.uikit.primitives.Button
import com.example.agoraagentcomposeuikit.uikit.primitives.Card
import com.example.agoraagentcomposeuikit.uikit.primitives.Chip
import com.example.agoraagentcomposeuikit.uikit.primitives.Command
import com.example.agoraagentcomposeuikit.uikit.primitives.DropdownMenu
import com.example.agoraagentcomposeuikit.uikit.primitives.DropdownMenuItem
import com.example.agoraagentcomposeuikit.uikit.primitives.DropdownMenuSeparator
import com.example.agoraagentcomposeuikit.uikit.primitives.Popover
import com.example.agoraagentcomposeuikit.uikit.primitives.ValuePicker
import com.example.agoraagentcomposeuikit.uikit.session.SessionPanel
import com.example.agoraagentcomposeuikit.uikit.settings.AgentSettings
import com.example.agoraagentcomposeuikit.uikit.settings.AgentSettingsPanel
import com.example.agoraagentcomposeuikit.uikit.settings.SettingsDialog
import com.example.agoraagentcomposeuikit.uikit.shen.ShenPanel
import com.example.agoraagentcomposeuikit.uikit.video.AgentAvatar
import com.example.agoraagentcomposeuikit.uikit.video.Avatar
import com.example.agoraagentcomposeuikit.uikit.video.AvatarVideoDisplay
import com.example.agoraagentcomposeuikit.uikit.video.CameraSelector
import com.example.agoraagentcomposeuikit.uikit.video.LocalVideoPreview
import com.example.agoraagentcomposeuikit.uikit.voice.AgentVisualizer
import com.example.agoraagentcomposeuikit.uikit.voice.AudioVisualizer
import com.example.agoraagentcomposeuikit.uikit.voice.LiveWaveform
import com.example.agoraagentcomposeuikit.uikit.voice.MicButton
import com.example.agoraagentcomposeuikit.uikit.voice.MicButtonWithVisualizer
import com.example.agoraagentcomposeuikit.uikit.voice.MicSelector
import com.example.agoraagentcomposeuikit.uikit.voice.SimpleVisualizer

@Composable
fun AgentUIKitShowcase() {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(WindowInsets.safeDrawing.asPaddingValues())
    ) {
        val compact = maxWidth < 760.dp
        ShowcaseScaffold(compact = compact)
    }
}

@Composable
private fun ShowcaseScaffold(compact: Boolean) {
    var activeTab by remember { mutableStateOf("voice") }
    val tabs = listOf(
        MobileTab("voice", "Voice"),
        MobileTab("chat", "Chat"),
        MobileTab("video", "Video"),
        MobileTab("system", "System")
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(if (compact) 16.dp else 20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item { ShowcaseHeader() }
        item {
            MobileTabs(
                tabs = tabs,
                activeTabId = activeTab,
                onTabSelected = { activeTab = it }
            )
        }
        item {
            when (activeTab) {
                "voice" -> VoicePlayground()
                "chat" -> ChatPlayground()
                "video" -> VideoPlayground()
                else -> SystemPlayground()
            }
        }
    }
}

@Composable
private fun ShowcaseHeader() {
    AgentCard(
        title = "Agent UIKit Playground",
        subtitle = "Smaller interactive demos so each component is easier to understand."
    ) {
        Text(
            text = "Use the tabs to explore one category at a time. Every section below has live state controls so the preview changes as you click.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun VoicePlayground() {
    var micState by remember { mutableStateOf(MicButtonState.Idle) }
    var micMuted by remember { mutableStateOf(false) }
    var selectedMic by remember { mutableStateOf("default") }
    var visualizerState by remember { mutableStateOf(AgentVisualizerState.Ambient) }
    var waveformActive by remember { mutableStateOf(true) }
    var micButtonEnabled by remember { mutableStateOf(true) }

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        DemoSection(
            title = "Mic Button",
            description = "Switch between idle, listening, processing, and error to feel the voice entry states."
        ) {
            MicButton(
                state = micState,
                audioData = when (micState) {
                    MicButtonState.Idle -> listOf(0f, 0f, 0f, 0f, 0f)
                    MicButtonState.Listening -> listOf(1f, 0f, 1f, 1f, 0f)
                    MicButtonState.Processing -> listOf(1f, 1f, 1f, 0f, 1f)
                    MicButtonState.Error -> listOf(0f, 1f, 0f, 1f, 0f)
                }
            )
            ControlButtons(
                "Idle" to { micState = MicButtonState.Idle },
                "Listening" to { micState = MicButtonState.Listening },
                "Processing" to { micState = MicButtonState.Processing },
                "Error" to { micState = MicButtonState.Error }
            )
        }

        DemoSection(
            title = "Mic Selector",
            description = "Toggle mute and switch devices to preview the selector behavior."
        ) {
            MicSelector(
                devices = micOptions(),
                value = selectedMic,
                onValueChange = { selectedMic = it },
                muted = micMuted,
                onMutedChange = { micMuted = it },
                state = micState
            )
            Text("Selected mic: ${micLabel(selectedMic)}", style = MaterialTheme.typography.bodyMedium)
            ControlButtons(
                if (micMuted) "Unmute" to { micMuted = false } else "Mute" to { micMuted = true },
                "Default" to { selectedMic = "default" },
                "USB" to { selectedMic = "usb" },
                "Bluetooth" to { selectedMic = "bt" }
            )
        }

        DemoSection(
            title = "Visualizer",
            description = "Switch the agent’s speech state and live waveform activity."
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AgentVisualizer(
                    state = visualizerState,
                    visualizerSize = AgentVisualizerSize.Small
                )
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    AudioVisualizer()
                    LiveWaveform(active = waveformActive)
                    SimpleVisualizer(data = listOf(1f, 0f, 1f, 1f, 0f, 1f, 0f, 1f))
                }
            }
            ControlButtons(
                "Ambient" to { visualizerState = AgentVisualizerState.Ambient },
                "Listening" to { visualizerState = AgentVisualizerState.Listening },
                "Analyzing" to { visualizerState = AgentVisualizerState.Analyzing },
                "Talking" to { visualizerState = AgentVisualizerState.Talking }
            )
            ControlButtons(
                if (waveformActive) "Waveform Off" to { waveformActive = false }
                else "Waveform On" to { waveformActive = true },
                if (micButtonEnabled) "Mic CTA Off" to { micButtonEnabled = false }
                else "Mic CTA On" to { micButtonEnabled = true }
            )
            MicButtonWithVisualizer(isEnabled = micButtonEnabled)
        }
    }
}

@Composable
private fun ChatPlayground() {
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

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
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
                "Clear" to {
                    messages.clear()
                }
            )
            if (messages.isEmpty()) {
                ConversationEmptyState()
            }
        }

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
}

@Composable
private fun VideoPlayground() {
    var avatarState by remember { mutableStateOf(AvatarVideoState.Connected) }
    var videoMuted by remember { mutableStateOf(false) }
    var selectedCamera by remember { mutableStateOf("front") }
    var cameraDisabled by remember { mutableStateOf(false) }
    var cameraError by remember { mutableStateOf(false) }
    var layoutMode by remember { mutableStateOf("grid") }

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        DemoSection(
            title = "Avatar and Video Surface",
            description = "Switch the avatar tile between loading, connected, and disconnected states."
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Avatar(name = "Akshay Anandwana")
                AgentAvatar(name = "Realtime Agent")
                AgoraLogo(modifier = Modifier.height(28.dp))
            }
            AvatarVideoDisplay(
                state = avatarState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            )
            ControlButtons(
                "Loading" to { avatarState = AvatarVideoState.Loading },
                "Connected" to { avatarState = AvatarVideoState.Connected },
                "Disconnected" to { avatarState = AvatarVideoState.Disconnected }
            )
        }

        DemoSection(
            title = "Local Preview and Camera Selector",
            description = "Preview muted video, change camera, and trigger a simulated camera error."
        ) {
            LocalVideoPreview(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                showLabel = true,
                label = if (videoMuted) "Camera Off" else "You",
                isVideoMuted = videoMuted
            )
            CameraSelector(
                devices = cameraOptions(),
                value = selectedCamera,
                onValueChange = { selectedCamera = it },
                disabled = cameraDisabled,
                onDisabledChange = { cameraDisabled = it },
                hasError = cameraError
            )
            Text("Selected camera: ${cameraLabel(selectedCamera)}", style = MaterialTheme.typography.bodyMedium)
            ControlButtons(
                if (videoMuted) "Camera On" to { videoMuted = false } else "Camera Off" to { videoMuted = true },
                "Front" to { selectedCamera = "front" },
                "Rear" to { selectedCamera = "rear" },
                if (cameraError) "Clear Error" to { cameraError = false } else "Show Error" to { cameraError = true }
            )
        }

        DemoSection(
            title = "Layout Playground",
            description = "Switch between the two library layout shells used for live agent sessions."
        ) {
            if (layoutMode == "grid") {
                VideoGrid(
                    chat = { Conversation(messages = sampleConversation()) },
                    avatar = {
                        AvatarVideoDisplay(
                            state = avatarState,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        )
                    },
                    localVideo = {
                        LocalVideoPreview(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp),
                            showLabel = true,
                            isVideoMuted = videoMuted
                        )
                    },
                    controls = { ControlStrip() }
                )
            } else {
                VideoGridWithControls(
                    chat = { Conversation(messages = sampleConversation().take(2)) },
                    avatar = {
                        AgentVisualizer(
                            state = AgentVisualizerState.Talking,
                            visualizerSize = AgentVisualizerSize.Small
                        )
                    },
                    localVideo = {
                        LocalVideoPreview(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp),
                            showLabel = true
                        )
                    },
                    controls = { ControlStrip() }
                )
            }
            ControlButtons(
                "VideoGrid" to { layoutMode = "grid" },
                "Grid + Controls" to { layoutMode = "split" }
            )
        }
    }
}

@Composable
private fun SystemPlayground() {
    var selectedPreset by remember { mutableStateOf("voice") }
    var selectedMenuAction by remember { mutableStateOf("Create session") }
    var menuExpanded by remember { mutableStateOf(false) }
    var settingsDialogOpen by remember { mutableStateOf(false) }
    var selectedMic by remember { mutableStateOf("usb") }
    var selectedLanguage by remember { mutableStateOf("en-US") }
    var turnDetection by remember { mutableStateOf(true) }
    var sessionRefreshCount by remember { mutableIntStateOf(1) }
    var shenConnected by remember { mutableStateOf(true) }

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        DemoSection(
            title = "Primitive Controls",
            description = "Tap the buttons and menu items to see the primitive layer respond."
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AgentButton(text = "Primary", onClick = { selectedMenuAction = "Primary clicked" })
                Button(text = "Compat", onClick = { selectedMenuAction = "Compat button clicked" })
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                AgentChip(text = "Agent", selected = true)
                Chip(text = "Android", selected = selectedPreset == "voice")
                Chip(text = "Compose", selected = selectedPreset == "video")
            }
            ValuePicker(
                items = listOf(
                    SettingOption("voice", "Voice-first"),
                    SettingOption("video", "Video-first"),
                    SettingOption("chat", "Chat-first")
                ),
                value = selectedPreset,
                onValueChange = { selectedPreset = it },
                label = "Preset"
            )
            Box {
                AgentButton(text = "Open Menu", onClick = { menuExpanded = true })
                DropdownMenu(
                    expanded = menuExpanded,
                    onDismissRequest = { menuExpanded = false }
                ) {
                    DropdownMenuItem(text = "Create session", onClick = {
                        selectedMenuAction = "Create session"
                        menuExpanded = false
                    })
                    DropdownMenuItem(text = "Open logs", onClick = {
                        selectedMenuAction = "Open logs"
                        menuExpanded = false
                    })
                    DropdownMenuSeparator()
                    DropdownMenuItem(text = "Disconnect", onClick = {
                        selectedMenuAction = "Disconnect"
                        menuExpanded = false
                    })
                }
            }
            Popover {
                Text(
                    text = "Last action: $selectedMenuAction",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Command(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
                items = listOf(
                    SettingOption("join", "Join room"),
                    SettingOption("mute", "Mute microphone"),
                    SettingOption("cam", "Turn camera off"),
                    SettingOption("leave", "Leave session")
                ),
                onItemSelected = { selectedMenuAction = it.name }
            )
        }

        DemoSection(
            title = "Settings and Dialog",
            description = "Adjust the state controls below, then see the settings surfaces update."
        ) {
            ControlButtons(
                "Mic: Default" to { selectedMic = "default" },
                "Mic: USB" to { selectedMic = "usb" },
                "Lang: EN" to { selectedLanguage = "en-US" },
                "Lang: HI" to { selectedLanguage = "hi-IN" }
            )
            ControlButtons(
                if (turnDetection) "Turn Detection Off" to { turnDetection = false }
                else "Turn Detection On" to { turnDetection = true },
                "Open Dialog" to { settingsDialogOpen = true }
            )
            AgentSettings(
                microphoneOptions = micOptions(),
                languageOptions = languageOptions(),
                selectedMicId = selectedMic,
                selectedLanguageId = selectedLanguage,
                enableTurnDetection = turnDetection,
                prompt = samplePrompt(),
                greeting = "Hi, how can I help today?"
            )
            AgentSettingsPanel(
                microphoneOptions = micOptions(),
                languageOptions = languageOptions(),
                selectedMicId = selectedMic,
                selectedLanguageId = selectedLanguage,
                enableTurnDetection = turnDetection,
                prompt = samplePrompt(),
                greeting = "Interactive showcase state is driving this panel."
            )
        }

        DemoSection(
            title = "Session, Branding, and Biomarkers",
            description = "Refresh the session payload or simulate connection changes for the Shen panel."
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                AgentButton(
                    text = "Refresh Session",
                    variant = AgentButtonVariant.Secondary,
                    onClick = { sessionRefreshCount += 1 }
                )
                AgentButton(
                    text = if (shenConnected) "Disconnect Biometrics" else "Connect Biometrics",
                    variant = AgentButtonVariant.Secondary,
                    onClick = { shenConnected = !shenConnected }
                )
            }
            SessionPanel(
                agentId = "agent_01JZ8YB9Q8N4B6C2XK5A",
                payloadPreview = """{"channel":"demo-room-$sessionRefreshCount","uid":"local-user","voice":"alloy"}"""
            )
            Card(title = "Branding") {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AgoraLogo(modifier = Modifier.height(30.dp))
                    Text("Agora Agent UIKit", style = MaterialTheme.typography.titleMedium)
                }
            }
            ShenPanel(
                shenState = ShenState(
                    sdkLoaded = true,
                    progress = if (shenConnected) 82 else 12,
                    heartRate = if (shenConnected) 72.0 else null,
                    hrvSdnn = if (shenConnected) 42.0 else null,
                    stressIndex = if (shenConnected) 18.0 else null,
                    breathingRate = if (shenConnected) 14.0 else null,
                    results = if (shenConnected) {
                        ShenMeasurementResults(
                            ageYears = 29.0,
                            heartRateBpm = 72.0,
                            hrvSdnnMs = 42.0,
                            stressIndex = 18.0,
                            breathingRateBpm = 14.0,
                            systolicBp = 118.0,
                            diastolicBp = 76.0,
                            signalQuality = 0.86
                        )
                    } else {
                        null
                    }
                ),
                isConnected = shenConnected
            )
        }
    }

    SettingsDialog(
        open = settingsDialogOpen,
        onOpenChange = { settingsDialogOpen = it },
        microphoneOptions = micOptions(),
        languageOptions = languageOptions(),
        selectedMicId = selectedMic,
        selectedLanguageId = selectedLanguage,
        enableTurnDetection = turnDetection,
        prompt = samplePrompt(),
        greeting = "Hi, how can I help today?"
    )
}

@Composable
private fun DemoSection(
    title: String,
    description: String,
    content: @Composable () -> Unit
) {
    AgentCard(title = title, subtitle = description) {
        content()
    }
}

@Composable
private fun ControlButtons(vararg actions: Pair<String, () -> Unit>) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        actions.toList().chunked(2).forEach { rowActions ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                rowActions.forEach { (label, action) ->
                    AgentButton(
                        text = label,
                        modifier = Modifier.weight(1f),
                        variant = AgentButtonVariant.Secondary,
                        onClick = action
                    )
                }
                if (rowActions.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

private fun micOptions(): List<SettingOption> = listOf(
    SettingOption("default", "System Default"),
    SettingOption("usb", "USB Podcast Mic"),
    SettingOption("bt", "Bluetooth Headset")
)

private fun languageOptions(): List<SettingOption> = listOf(
    SettingOption("en-US", "English (US)"),
    SettingOption("hi-IN", "Hindi"),
    SettingOption("ja-JP", "Japanese")
)

private fun cameraOptions(): List<CameraDevice> = listOf(
    CameraDevice("front", "Front Camera"),
    CameraDevice("rear", "Rear Camera")
)

private fun micLabel(id: String): String = micOptions().firstOrNull { it.id == id }?.name ?: id

private fun cameraLabel(id: String): String = cameraOptions().firstOrNull { it.deviceId == id }?.label ?: id

private fun samplePrompt(): String =
    "You are a helpful real-time voice assistant. Keep responses short, clear, and action-oriented."

private fun sampleConversation(): List<ConversationMessage> = listOf(
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
