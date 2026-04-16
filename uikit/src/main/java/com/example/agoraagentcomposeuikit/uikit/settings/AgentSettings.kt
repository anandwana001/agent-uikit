package com.example.agoraagentcomposeuikit.uikit.settings

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.agoraagentcomposeuikit.uikit.models.SettingOption

@Composable
fun AgentSettings(
    microphoneOptions: List<SettingOption>,
    languageOptions: List<SettingOption>,
    selectedMicId: String,
    selectedLanguageId: String,
    enableTurnDetection: Boolean,
    prompt: String,
    greeting: String,
    modifier: Modifier = Modifier
) {
    AgentSettingsPanel(
        microphoneOptions = microphoneOptions,
        languageOptions = languageOptions,
        selectedMicId = selectedMicId,
        selectedLanguageId = selectedLanguageId,
        enableTurnDetection = enableTurnDetection,
        prompt = prompt,
        greeting = greeting,
        modifier = modifier
    )
}
