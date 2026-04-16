package com.example.agoraagentcomposeuikit.demo.showcase

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.agoraagentcomposeuikit.uikit.branding.AgoraLogo
import com.example.agoraagentcomposeuikit.uikit.models.SettingOption
import com.example.agoraagentcomposeuikit.uikit.models.ShenMeasurementResults
import com.example.agoraagentcomposeuikit.uikit.models.ShenState
import com.example.agoraagentcomposeuikit.uikit.primitives.AgentButton
import com.example.agoraagentcomposeuikit.uikit.primitives.AgentButtonVariant
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

@Composable
fun SystemShowcase() {
    var activeDemo by remember { mutableStateOf("primitives") }

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        ComponentPicker(
            options = listOf(
                ShowcaseOption("primitives", "Primitives"),
                ShowcaseOption("settings", "Settings"),
                ShowcaseOption("session", "Session & Biometrics")
            ),
            selectedId = activeDemo,
            onSelect = { activeDemo = it }
        )
        when (activeDemo) {
            "primitives" -> PrimitivesDemo()
            "settings" -> SettingsDemo()
            else -> SessionAndBiometricsDemo()
        }
    }
}

@Composable
private fun PrimitivesDemo() {
    var selectedPreset by remember { mutableStateOf("voice") }
    var selectedMenuAction by remember { mutableStateOf("Create session") }
    var menuExpanded by remember { mutableStateOf(false) }

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
}

@Composable
private fun SettingsDemo() {
    var settingsDialogOpen by remember { mutableStateOf(false) }
    var selectedMic by remember { mutableStateOf("usb") }
    var selectedLanguage by remember { mutableStateOf("en-US") }
    var turnDetection by remember { mutableStateOf(true) }

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
private fun SessionAndBiometricsDemo() {
    var sessionRefreshCount by remember { mutableIntStateOf(1) }
    var shenConnected by remember { mutableStateOf(true) }

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
