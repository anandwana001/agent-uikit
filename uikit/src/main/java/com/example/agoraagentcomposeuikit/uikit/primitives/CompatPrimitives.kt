package com.example.agoraagentcomposeuikit.uikit.primitives

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun Button(
    text: String,
    modifier: Modifier = Modifier,
    variant: AgentButtonVariant = AgentButtonVariant.Primary,
    onClick: () -> Unit = {}
) = AgentButton(text = text, modifier = modifier, variant = variant, onClick = onClick)

@Composable
fun IconButton(
    icon: ImageVector,
    contentDescription: String,
    modifier: Modifier = Modifier,
    filled: Boolean = true
) = AgentIconButton(icon = icon, contentDescription = contentDescription, modifier = modifier, filled = filled)

@Composable
fun Card(
    modifier: Modifier = Modifier,
    title: String? = null,
    subtitle: String? = null,
    content: @Composable androidx.compose.foundation.layout.ColumnScope.() -> Unit
) = AgentCard(modifier = modifier, title = title, subtitle = subtitle, content = content)

@Composable
fun Chip(
    text: String,
    modifier: Modifier = Modifier,
    selected: Boolean = false
) = AgentChip(text = text, modifier = modifier, selected = selected)
