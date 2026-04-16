package com.example.agoraagentcomposeuikit.uikit.primitives

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.agoraagentcomposeuikit.ui.theme.LocalAgentUIKitPalette

@Composable
fun AgentChip(
    text: String,
    modifier: Modifier = Modifier,
    selected: Boolean = false
) {
    val palette = LocalAgentUIKitPalette.current
    FilterChip(
        selected = selected,
        onClick = {},
        enabled = false,
        modifier = modifier,
        label = { Text(text) },
        border = BorderStroke(1.dp, if (selected) palette.primaryBlue else palette.borderStrong),
        colors = FilterChipDefaults.filterChipColors(
            containerColor = palette.cardLayer2,
            disabledContainerColor = palette.cardLayer2,
            selectedContainerColor = palette.cardLayer3,
            disabledLabelColor = palette.textMedium,
            labelColor = palette.textMedium,
            selectedLabelColor = palette.textHigh
        )
    )
}
