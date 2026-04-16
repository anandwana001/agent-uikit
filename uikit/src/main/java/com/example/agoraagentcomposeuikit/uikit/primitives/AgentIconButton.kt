package com.example.agoraagentcomposeuikit.uikit.primitives

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.agoraagentcomposeuikit.ui.theme.LocalAgentUIKitPalette

@Composable
fun AgentIconButton(
    icon: ImageVector,
    contentDescription: String,
    modifier: Modifier = Modifier,
    filled: Boolean = true
) {
    val palette = LocalAgentUIKitPalette.current
    Surface(
        modifier = modifier,
        shape = CircleShape,
        color = if (filled) palette.primaryBlue else palette.cardLayer2,
        border = if (filled) null else BorderStroke(1.dp, palette.borderStrong)
    ) {
        Box(
            modifier = Modifier.size(48.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                tint = palette.textHigh
            )
        }
    }
}
