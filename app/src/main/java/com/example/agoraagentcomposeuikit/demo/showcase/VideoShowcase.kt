package com.example.agoraagentcomposeuikit.demo.showcase

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.agoraagentcomposeuikit.uikit.branding.AgoraLogo
import com.example.agoraagentcomposeuikit.uikit.chat.Conversation
import com.example.agoraagentcomposeuikit.uikit.layout.ControlStrip
import com.example.agoraagentcomposeuikit.uikit.layout.VideoGrid
import com.example.agoraagentcomposeuikit.uikit.layout.VideoGridWithControls
import com.example.agoraagentcomposeuikit.uikit.models.AgentVisualizerSize
import com.example.agoraagentcomposeuikit.uikit.models.AgentVisualizerState
import com.example.agoraagentcomposeuikit.uikit.models.AvatarVideoState
import com.example.agoraagentcomposeuikit.uikit.video.AgentAvatar
import com.example.agoraagentcomposeuikit.uikit.video.Avatar
import com.example.agoraagentcomposeuikit.uikit.video.AvatarVideoDisplay
import com.example.agoraagentcomposeuikit.uikit.video.CameraSelector
import com.example.agoraagentcomposeuikit.uikit.video.LocalVideoPreview
import com.example.agoraagentcomposeuikit.uikit.voice.AgentVisualizer

@Composable
fun VideoShowcase() {
    var activeDemo by remember { mutableStateOf("avatar") }

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        ComponentPicker(
            options = listOf(
                ShowcaseOption("avatar", "Avatar Surface"),
                ShowcaseOption("camera", "Camera Selector"),
                ShowcaseOption("layout", "Layout Playground")
            ),
            selectedId = activeDemo,
            onSelect = { activeDemo = it }
        )
        when (activeDemo) {
            "avatar" -> AvatarDemo()
            "camera" -> CameraDemo()
            else -> LayoutDemo()
        }
    }
}

@Composable
private fun AvatarDemo() {
    var avatarState by remember { mutableStateOf(AvatarVideoState.Connected) }

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
}

@Composable
private fun CameraDemo() {
    var videoMuted by remember { mutableStateOf(false) }
    var selectedCamera by remember { mutableStateOf("front") }
    var cameraDisabled by remember { mutableStateOf(false) }
    var cameraError by remember { mutableStateOf(false) }

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
}

@Composable
private fun LayoutDemo() {
    var avatarState by remember { mutableStateOf(AvatarVideoState.Connected) }
    var videoMuted by remember { mutableStateOf(false) }
    var layoutMode by remember { mutableStateOf("grid") }

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
            "Grid + Controls" to { layoutMode = "split" },
            if (videoMuted) "Camera On" to { videoMuted = false } else "Camera Off" to { videoMuted = true }
        )
    }
}
