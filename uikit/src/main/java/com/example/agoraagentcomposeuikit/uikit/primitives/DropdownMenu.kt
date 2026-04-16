package com.example.agoraagentcomposeuikit.uikit.primitives

import androidx.compose.material3.DropdownMenu as MaterialDropdownMenu
import androidx.compose.material3.DropdownMenuItem as MaterialDropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AgentDropdownMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    MaterialDropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        content = { content() }
    )
}

@Composable
fun DropdownMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) = AgentDropdownMenu(
    expanded = expanded,
    onDismissRequest = onDismissRequest,
    modifier = modifier,
    content = content
)

@Composable
fun AgentDropdownMenuItem(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    MaterialDropdownMenuItem(
        text = { Text(text) },
        onClick = onClick,
        modifier = modifier
    )
}

@Composable
fun DropdownMenuItem(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) = AgentDropdownMenuItem(text = text, onClick = onClick, modifier = modifier)

@Composable
fun AgentDropdownMenuSeparator() {
    HorizontalDivider()
}

@Composable
fun DropdownMenuSeparator() = AgentDropdownMenuSeparator()
