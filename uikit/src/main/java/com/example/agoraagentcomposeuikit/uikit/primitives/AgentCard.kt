package com.example.agoraagentcomposeuikit.uikit.primitives

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.agoraagentcomposeuikit.ui.theme.LocalAgentUIKitPalette

@Composable
fun AgentCard(
    modifier: Modifier = Modifier,
    title: String? = null,
    subtitle: String? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val palette = LocalAgentUIKitPalette.current
    Surface(
        modifier = modifier.border(
            border = BorderStroke(1.dp, palette.borderSubtle),
            shape = RoundedCornerShape(12.dp)
        ),
        color = palette.cardLayer1,
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .background(palette.cardLayer1)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (title != null || subtitle != null) {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    title?.let {
                        Text(it, style = MaterialTheme.typography.titleMedium)
                    }
                    subtitle?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
            content()
        }
    }
}
