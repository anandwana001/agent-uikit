package com.example.agoraagentcomposeuikit.uikit.primitives

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.agoraagentcomposeuikit.ui.theme.LocalAgentUIKitPalette

@Composable
fun PopoverContent(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val palette = LocalAgentUIKitPalette.current
    Surface(
        modifier = modifier,
        color = palette.cardLayer1,
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, palette.borderSubtle)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            content()
        }
    }
}

@Composable
fun Popover(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) = PopoverContent(modifier = modifier, content = content)
