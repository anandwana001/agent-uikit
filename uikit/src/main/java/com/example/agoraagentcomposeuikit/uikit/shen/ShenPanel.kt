package com.example.agoraagentcomposeuikit.uikit.shen

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.agoraagentcomposeuikit.uikit.models.ShenState
import com.example.agoraagentcomposeuikit.uikit.primitives.AgentCard

@Composable
fun ShenPanel(
    shenState: ShenState,
    isConnected: Boolean,
    modifier: Modifier = Modifier
) {
    AgentCard(
        modifier = modifier,
        title = "Shen.AI",
        subtitle = if (isConnected) "Camera vitals panel" else "Connect to start camera vitals"
    ) {
        if (!isConnected) {
            Text("Connect to start camera vitals", style = MaterialTheme.typography.bodyMedium)
            return@AgentCard
        }
        if (!shenState.sdkLoaded) {
            Text("Loading Shen.AI SDK...", style = MaterialTheme.typography.bodyMedium)
            return@AgentCard
        }
        Text("Measurement Progress: ${shenState.progress}%", style = MaterialTheme.typography.labelLarge)
        VitalRow("Heart Rate", shenState.heartRate ?: shenState.realtimeHr, "bpm")
        VitalRow("HRV (SDNN)", shenState.hrvSdnn, "ms")
        VitalRow("Stress Index", shenState.stressIndex, "")
        VitalRow("Breathing Rate", shenState.breathingRate, "bpm")
        shenState.results?.let { results ->
            VitalRow("Estimated Age", results.ageYears, "yrs")
            VitalRow("Systolic BP", results.systolicBp, "mmHg")
            VitalRow("Diastolic BP", results.diastolicBp, "mmHg")
            VitalRow("Signal Quality", results.signalQuality?.times(100), "%")
        }
    }
}

@Composable
private fun VitalRow(label: String, value: Double?, unit: String) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(label, style = MaterialTheme.typography.bodyMedium)
            Text(
                text = if (value == null) "—" else "${if (value % 1.0 == 0.0) value.toInt() else String.format("%.1f", value)} $unit".trim(),
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}
