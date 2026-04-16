package com.example.agoraagentcomposeuikit.demo.showcase

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.agoraagentcomposeuikit.uikit.primitives.AgentButton
import com.example.agoraagentcomposeuikit.uikit.primitives.AgentButtonVariant
import com.example.agoraagentcomposeuikit.uikit.primitives.AgentCard

data class ShowcaseOption(
    val id: String,
    val label: String
)

@Composable
fun ShowcaseHeader() {
    AgentCard(
        title = "Agent UIKit Playground",
        subtitle = "Pick a category, then focus on one component demo at a time."
    ) {
        Text(
            text = "Each category now behaves like a guided playground. Use the inner selector to switch between components and the action buttons to change the live preview state.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun DemoSection(
    title: String,
    description: String,
    content: @Composable () -> Unit
) {
    AgentCard(title = title, subtitle = description) {
        content()
    }
}

@Composable
fun ComponentPicker(
    options: List<ShowcaseOption>,
    selectedId: String,
    onSelect: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        options.chunked(2).forEach { rowOptions ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                rowOptions.forEach { option ->
                    AgentButton(
                        text = option.label,
                        modifier = Modifier.weight(1f),
                        variant = if (option.id == selectedId) {
                            AgentButtonVariant.Primary
                        } else {
                            AgentButtonVariant.Secondary
                        },
                        onClick = { onSelect(option.id) }
                    )
                }
                if (rowOptions.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun ControlButtons(vararg actions: Pair<String, () -> Unit>) {
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
