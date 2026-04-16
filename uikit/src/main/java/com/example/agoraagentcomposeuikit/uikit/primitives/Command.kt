package com.example.agoraagentcomposeuikit.uikit.primitives

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.agoraagentcomposeuikit.uikit.models.SettingOption

@Composable
fun Command(
    items: List<SettingOption>,
    modifier: Modifier = Modifier,
    placeholder: String = "Search...",
    emptyText: String = "No results",
    onItemSelected: (SettingOption) -> Unit = {}
) {
    var query by remember { mutableStateOf("") }
    val filtered = items.filter {
        query.isBlank() || it.name.contains(query, ignoreCase = true)
    }
    AgentCard(modifier = modifier) {
        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(placeholder) },
            singleLine = true
        )
        if (filtered.isEmpty()) {
            Text(emptyText, style = MaterialTheme.typography.bodyMedium)
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                items(filtered, key = { it.id }) { item ->
                    Text(
                        text = item.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onItemSelected(item) }
                            .padding(vertical = 8.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}
