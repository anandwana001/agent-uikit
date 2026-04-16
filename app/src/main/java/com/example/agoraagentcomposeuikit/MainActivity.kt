package com.example.agoraagentcomposeuikit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.agoraagentcomposeuikit.demo.AgentUIKitShowcase
import com.example.agoraagentcomposeuikit.ui.theme.AgoraAgentComposeUIKitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AgoraAgentComposeUIKitTheme(darkTheme = true) {
                AgentUIKitShowcase()
            }
        }
    }
}
