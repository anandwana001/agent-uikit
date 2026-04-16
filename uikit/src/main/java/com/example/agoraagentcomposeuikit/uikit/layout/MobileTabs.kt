package com.example.agoraagentcomposeuikit.uikit.layout

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.agoraagentcomposeuikit.ui.theme.LocalAgentUIKitPalette
import com.example.agoraagentcomposeuikit.uikit.models.MobileTab

@Composable
fun MobileTabs(
    tabs: List<MobileTab>,
    activeTabId: String,
    onTabSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val palette = LocalAgentUIKitPalette.current
    Surface(
        modifier = modifier,
        color = palette.cardLayer1,
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, palette.borderSubtle)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            tabs.forEach { tab ->
                val selected = tab.id == activeTabId
                Surface(
                    modifier = Modifier.weight(1f),
                    onClick = { onTabSelected(tab.id) },
                    color = if (selected) palette.cardLayer3 else palette.cardLayer1,
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, if (selected) palette.primaryBlue else palette.borderSubtle)
                ) {
                    Column(
                        modifier = Modifier.padding(vertical = 10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = tab.label,
                            style = MaterialTheme.typography.labelLarge,
                            color = if (selected) palette.textHigh else palette.textMedium
                        )
                    }
                }
            }
        }
    }
}
