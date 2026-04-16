package com.example.agoraagentcomposeuikit.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.agoraagentcomposeuikit.demo.showcase.ChatShowcase
import com.example.agoraagentcomposeuikit.demo.showcase.ShowcaseHeader
import com.example.agoraagentcomposeuikit.demo.showcase.SystemShowcase
import com.example.agoraagentcomposeuikit.demo.showcase.VideoShowcase
import com.example.agoraagentcomposeuikit.demo.showcase.VoiceShowcase
import com.example.agoraagentcomposeuikit.ui.theme.Background
import com.example.agoraagentcomposeuikit.uikit.layout.MobileTabs
import com.example.agoraagentcomposeuikit.uikit.models.MobileTab

@Composable
fun AgentUIKitShowcase() {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(WindowInsets.safeDrawing.asPaddingValues())
    ) {
        val compact = maxWidth < 760.dp
        var activeTab by remember { mutableStateOf("voice") }
        val tabs = listOf(
            MobileTab("voice", "Voice"),
            MobileTab("chat", "Chat"),
            MobileTab("video", "Video"),
            MobileTab("system", "System")
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(if (compact) 16.dp else 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item { ShowcaseHeader() }
            item {
                MobileTabs(
                    tabs = tabs,
                    activeTabId = activeTab,
                    onTabSelected = { activeTab = it }
                )
            }
            item {
                when (activeTab) {
                    "voice" -> VoiceShowcase()
                    "chat" -> ChatShowcase()
                    "video" -> VideoShowcase()
                    else -> SystemShowcase()
                }
            }
        }
    }
}
