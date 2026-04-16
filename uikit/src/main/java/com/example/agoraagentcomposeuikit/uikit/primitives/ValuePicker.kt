package com.example.agoraagentcomposeuikit.uikit.primitives

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.ExpandMore
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
import com.example.agoraagentcomposeuikit.uikit.models.SettingOption

@Composable
fun ValuePicker(
    items: List<SettingOption>,
    value: String? = null,
    onValueChange: (String) -> Unit = {},
    placeholder: String = "Select a value...",
    label: String? = null,
    disabled: Boolean = false,
    modifier: Modifier = Modifier
) {
    val palette = LocalAgentUIKitPalette.current
    val expanded = remember { mutableStateOf(false) }
    val selected = items.firstOrNull { it.id == value }
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        label?.let { Text(it, style = MaterialTheme.typography.labelLarge) }
        Surface(
            color = palette.cardLayer2,
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, palette.borderSubtle),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                androidx.compose.foundation.layout.Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(enabled = !disabled) { expanded.value = true }
                        .padding(horizontal = 12.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(selected?.name ?: placeholder, style = MaterialTheme.typography.bodyMedium)
                    Icon(Icons.Outlined.ExpandMore, contentDescription = null)
                }
                DropdownMenu(
                    expanded = expanded.value,
                    onDismissRequest = { expanded.value = false }
                ) {
                    if (items.isEmpty()) {
                        DropdownMenuItem(
                            text = { Text("No items found") },
                            onClick = { expanded.value = false }
                        )
                    } else {
                        items.forEach { item ->
                            DropdownMenuItem(
                                text = { Text(item.name) },
                                onClick = {
                                    expanded.value = false
                                    onValueChange(item.id)
                                },
                                trailingIcon = {
                                    if (item.id == value) {
                                        Icon(Icons.Outlined.Check, contentDescription = null)
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
