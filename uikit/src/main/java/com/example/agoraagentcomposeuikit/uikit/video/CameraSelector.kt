package com.example.agoraagentcomposeuikit.uikit.video

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material.icons.outlined.Videocam
import androidx.compose.material.icons.outlined.VideocamOff
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.agoraagentcomposeuikit.ui.theme.LocalAgentUIKitPalette
import com.example.agoraagentcomposeuikit.uikit.models.CameraDevice

@Composable
fun CameraSelector(
    devices: List<CameraDevice> = emptyList(),
    value: String? = null,
    onValueChange: (String) -> Unit = {},
    disabled: Boolean = false,
    onDisabledChange: (Boolean) -> Unit = {},
    hasError: Boolean = false,
    modifier: Modifier = Modifier
) {
    val palette = LocalAgentUIKitPalette.current
    val expanded = remember { mutableStateOf(false) }
    val selected = devices.firstOrNull { it.deviceId == value } ?: devices.firstOrNull()
    Surface(
        modifier = modifier,
        color = palette.cardLayer2,
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, if (hasError) palette.error else palette.borderStrong)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Surface(
                onClick = { onDisabledChange(!disabled) },
                shape = RoundedCornerShape(999.dp),
                color = if (disabled) palette.cardLayer3 else palette.primaryBlue.copy(alpha = 0.15f)
            ) {
                Icon(
                    imageVector = if (disabled || hasError) Icons.Outlined.VideocamOff else Icons.Outlined.Videocam,
                    contentDescription = null,
                    modifier = Modifier.padding(8.dp)
                )
            }
            Text(
                text = selected?.label ?: "Select camera",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(8.dp))
            )
            if (hasError) {
                Icon(Icons.Outlined.ErrorOutline, contentDescription = null, tint = palette.error)
            } else {
                Surface(onClick = { expanded.value = true }, color = palette.cardLayer1, shape = RoundedCornerShape(8.dp)) {
                    Text("Choose", modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp))
                }
                DropdownMenu(expanded = expanded.value, onDismissRequest = { expanded.value = false }) {
                    if (devices.isEmpty()) {
                        DropdownMenuItem(text = { Text("No cameras available") }, onClick = { expanded.value = false })
                    } else {
                        devices.forEach { device ->
                            DropdownMenuItem(
                                text = { Text(device.label) },
                                onClick = {
                                    expanded.value = false
                                    onValueChange(device.deviceId)
                                },
                                trailingIcon = {
                                    if (device.deviceId == value) Icon(Icons.Outlined.Check, contentDescription = null)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
