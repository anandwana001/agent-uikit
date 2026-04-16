package com.example.agoraagentcomposeuikit.uikit.voice

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material.icons.outlined.Mic
import androidx.compose.material.icons.outlined.MicOff
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.agoraagentcomposeuikit.ui.theme.LocalAgentUIKitPalette
import com.example.agoraagentcomposeuikit.uikit.models.MicButtonState
import com.example.agoraagentcomposeuikit.uikit.models.SettingOption

@Composable
fun MicSelector(
    devices: List<SettingOption> = emptyList(),
    value: String? = null,
    onValueChange: (String) -> Unit = {},
    muted: Boolean = false,
    onMutedChange: (Boolean) -> Unit = {},
    state: MicButtonState = MicButtonState.Idle,
    modifier: Modifier = Modifier
) {
    val palette = LocalAgentUIKitPalette.current
    val expanded = remember { mutableStateOf(false) }
    val selected = devices.firstOrNull { it.id == value } ?: devices.firstOrNull()
    Surface(
        modifier = modifier,
        color = palette.cardLayer2,
        shape = RoundedCornerShape(999.dp),
        border = BorderStroke(1.dp, if (state == MicButtonState.Error) palette.error else palette.borderStrong)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Surface(
                onClick = { onMutedChange(!muted) },
                shape = RoundedCornerShape(999.dp),
                color = palette.cardLayer1
            ) {
                Icon(
                    imageVector = if (muted || state == MicButtonState.Error) Icons.Outlined.MicOff else Icons.Outlined.Mic,
                    contentDescription = null,
                    modifier = Modifier.padding(8.dp)
                )
            }
            LiveWaveform(
                active = !muted,
                modifier = Modifier.weight(1f)
            )
            Surface(onClick = { expanded.value = true }, shape = RoundedCornerShape(8.dp), color = palette.cardLayer1) {
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = selected?.name ?: "Select mic",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Icon(Icons.Outlined.ExpandMore, contentDescription = null)
                }
            }
            DropdownMenu(expanded = expanded.value, onDismissRequest = { expanded.value = false }) {
                devices.forEach { device ->
                    DropdownMenuItem(
                        text = { Text(device.name) },
                        onClick = {
                            expanded.value = false
                            onValueChange(device.id)
                        },
                        trailingIcon = {
                            if (device.id == value) Icon(Icons.Outlined.Check, contentDescription = null)
                        }
                    )
                }
            }
        }
    }
}
