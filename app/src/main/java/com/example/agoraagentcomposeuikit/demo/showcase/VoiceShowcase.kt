package com.example.agoraagentcomposeuikit.demo.showcase

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.example.agoraagentcomposeuikit.uikit.models.AgentVisualizerSize
import com.example.agoraagentcomposeuikit.uikit.models.AgentVisualizerState
import com.example.agoraagentcomposeuikit.uikit.models.MicButtonState
import com.example.agoraagentcomposeuikit.uikit.voice.AgentVisualizer
import com.example.agoraagentcomposeuikit.uikit.voice.AudioVisualizer
import com.example.agoraagentcomposeuikit.uikit.voice.LiveWaveform
import com.example.agoraagentcomposeuikit.uikit.voice.MicButton
import com.example.agoraagentcomposeuikit.uikit.voice.MicButtonWithVisualizer
import com.example.agoraagentcomposeuikit.uikit.voice.MicSelector
import com.example.agoraagentcomposeuikit.uikit.voice.SimpleVisualizer

@Composable
fun VoiceShowcase() {
    var activeDemo by remember { mutableStateOf("mic-button") }

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        ComponentPicker(
            options = listOf(
                ShowcaseOption("mic-button", "Mic Button"),
                ShowcaseOption("mic-selector", "Mic Selector"),
                ShowcaseOption("visualizer", "Visualizer")
            ),
            selectedId = activeDemo,
            onSelect = { activeDemo = it }
        )
        when (activeDemo) {
            "mic-button" -> MicButtonDemo()
            "mic-selector" -> MicSelectorDemo()
            else -> VisualizerDemo()
        }
    }
}

@Composable
private fun MicButtonDemo() {
    var micState by remember { mutableStateOf(MicButtonState.Idle) }

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
}

@Composable
private fun MicSelectorDemo() {
    var micState by remember { mutableStateOf(MicButtonState.Listening) }
    var micMuted by remember { mutableStateOf(false) }
    var selectedMic by remember { mutableStateOf("default") }

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
        androidx.compose.material3.Text(
            text = "Selected mic: ${micLabel(selectedMic)}",
            style = androidx.compose.material3.MaterialTheme.typography.bodyMedium
        )
        ControlButtons(
            if (micMuted) "Unmute" to { micMuted = false } else "Mute" to { micMuted = true },
            "Default" to { selectedMic = "default" },
            "USB" to { selectedMic = "usb" },
            "Bluetooth" to { selectedMic = "bt" },
            "Error State" to { micState = MicButtonState.Error },
            "Normal State" to { micState = MicButtonState.Listening }
        )
    }
}

@Composable
private fun VisualizerDemo() {
    var visualizerState by remember { mutableStateOf(AgentVisualizerState.Ambient) }
    var waveformActive by remember { mutableStateOf(true) }
    var micButtonEnabled by remember { mutableStateOf(true) }

    DemoSection(
        title = "Visualizer",
        description = "Switch the agent’s speech state and live waveform activity."
    ) {
        Row(
            modifier = androidx.compose.ui.Modifier.fillMaxWidth(),
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
