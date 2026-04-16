package com.example.agoraagentcomposeuikit.uikit.primitives

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.agoraagentcomposeuikit.ui.theme.LocalAgentUIKitPalette

@Composable
fun AgentButton(
    text: String,
    modifier: Modifier = Modifier,
    variant: AgentButtonVariant = AgentButtonVariant.Primary,
    onClick: () -> Unit = {}
) {
    val palette = LocalAgentUIKitPalette.current
    val background = when (variant) {
        AgentButtonVariant.Primary -> palette.primaryBlue
        AgentButtonVariant.Secondary -> Color.Transparent
    }
    val border = when (variant) {
        AgentButtonVariant.Primary -> null
        AgentButtonVariant.Secondary -> BorderStroke(1.dp, palette.borderStrong)
    }
    Surface(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        color = background,
        border = border
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge,
                color = if (variant == AgentButtonVariant.Primary) palette.textHigh else palette.textMedium
            )
        }
    }
}

enum class AgentButtonVariant {
    Primary,
    Secondary
}
