package com.example.agoraagentcomposeuikit.uikit.video

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Avatar(
    name: String,
    modifier: Modifier = Modifier
) = AgentAvatar(name = name, modifier = modifier)
