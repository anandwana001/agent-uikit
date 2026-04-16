package com.example.agoraagentcomposeuikit.uikit.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.agoraagentcomposeuikit.uikit.models.SettingOption

@Composable
fun SettingsDialog(
    open: Boolean,
    onOpenChange: (Boolean) -> Unit,
    microphoneOptions: List<SettingOption>,
    languageOptions: List<SettingOption>,
    selectedMicId: String,
    selectedLanguageId: String,
    enableTurnDetection: Boolean,
    prompt: String,
    greeting: String,
    title: String = "Agent Settings",
    description: String = "Configure your agent settings and session preferences.",
    extraContent: @Composable (() -> Unit)? = null
) {
    if (!open) return
    AlertDialog(
        onDismissRequest = { onOpenChange(false) },
        title = { Text(title) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(description, style = MaterialTheme.typography.bodyMedium)
                AgentSettingsPanel(
                    microphoneOptions = microphoneOptions,
                    languageOptions = languageOptions,
                    selectedMicId = selectedMicId,
                    selectedLanguageId = selectedLanguageId,
                    enableTurnDetection = enableTurnDetection,
                    prompt = prompt,
                    greeting = greeting,
                    modifier = Modifier.fillMaxWidth()
                )
                extraContent?.invoke()
            }
        },
        confirmButton = {
            TextButton(onClick = { onOpenChange(false) }) {
                Text("Close")
            }
        }
    )
}
