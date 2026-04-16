package com.example.agoraagentcomposeuikit.uikit.utils

fun decodeStreamMessage(bytes: ByteArray): String = bytes.decodeToString()

fun renderMarkdownToPlainText(text: String): String {
    return text
        .replace(Regex("\\*\\*([^*]+)\\*\\*"), "$1")
        .replace(Regex("\\[([^\\]]+)]\\(([^)]+)\\)"), "$1: $2")
}
