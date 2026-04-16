package com.example.agoraagentcomposeuikit.uikit.settings

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.agoraagentcomposeuikit.ui.theme.LocalAgentUIKitPalette
import com.example.agoraagentcomposeuikit.uikit.models.SettingOption
import com.example.agoraagentcomposeuikit.uikit.primitives.AgentCard

@Composable
fun AgentSettingsPanel(
    microphoneOptions: List<SettingOption>,
    languageOptions: List<SettingOption>,
    selectedMicId: String,
    selectedLanguageId: String,
    enableTurnDetection: Boolean,
    prompt: String,
    greeting: String,
    modifier: Modifier = Modifier
) {
    AgentCard(
        modifier = modifier,
        title = "Agent Settings",
        subtitle = "Microphone, language, turn detection, prompt, and greeting."
    ) {
        SettingPicker("Microphone", microphoneOptions.firstOrNull { it.id == selectedMicId }?.name ?: "System Default")
        SettingPicker("Speech Recognition Language", languageOptions.firstOrNull { it.id == selectedLanguageId }?.name ?: selectedLanguageId)
        TurnDetectionRow(enableTurnDetection)
        SettingField("System Prompt", prompt, minHeight = 120.dp)
        SettingField("Greeting Message", greeting, minHeight = 56.dp)
    }
}

@Composable
private fun SettingPicker(label: String, value: String) {
    val palette = LocalAgentUIKitPalette.current
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(label, style = MaterialTheme.typography.labelLarge)
        Surface(
            color = palette.cardLayer2,
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, palette.borderSubtle)
        ) {
            Text(
                text = value,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 12.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
private fun TurnDetectionRow(enabled: Boolean) {
    val palette = LocalAgentUIKitPalette.current
    Surface(
        color = palette.cardLayer2,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, palette.borderSubtle)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("AI Turn Detection", style = MaterialTheme.typography.labelLarge)
            Switch(checked = enabled, onCheckedChange = {})
        }
    }
}

@Composable
private fun SettingField(label: String, value: String, minHeight: Dp) {
    val palette = LocalAgentUIKitPalette.current
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(label, style = MaterialTheme.typography.labelLarge)
        Surface(
            color = palette.cardLayer2,
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, palette.borderSubtle)
        ) {
            BasicTextField(
                value = value,
                onValueChange = {},
                readOnly = true,
                textStyle = MaterialTheme.typography.bodyMedium.copy(color = palette.textHigh),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                    .heightIn(min = minHeight)
            )
        }
    }
}
