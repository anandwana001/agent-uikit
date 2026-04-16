package com.example.agoraagentcomposeuikit.uikit.utils

data class RemoteThemeConfig(
    val primaryColor: String? = null,
    val primaryActionBrandColor: String? = null,
    val fontColor: String? = null,
    val backgroundColor: String? = null,
    val cardLayer1Color: String? = null,
    val cardLayer2Color: String? = null,
    val cardLayer3Color: String? = null,
    val semanticError: String? = null,
    val semanticSuccess: String? = null,
    val semanticWarning: String? = null
)

private var currentTheme: RemoteThemeConfig = RemoteThemeConfig()

fun applyCustomTheme(config: RemoteThemeConfig) {
    currentTheme = config
}

fun getCurrentTheme(): RemoteThemeConfig = currentTheme

fun hexToRgbString(hex: String): String? {
    var value = hex.trim()
    if (!value.startsWith("#")) return null
    value = value.removePrefix("#")
    return when (value.length) {
        3 -> {
            val r = Integer.parseInt("${value[0]}${value[0]}", 16)
            val g = Integer.parseInt("${value[1]}${value[1]}", 16)
            val b = Integer.parseInt("${value[2]}${value[2]}", 16)
            "$r, $g, $b"
        }
        6 -> {
            val r = Integer.parseInt(value.substring(0, 2), 16)
            val g = Integer.parseInt(value.substring(2, 4), 16)
            val b = Integer.parseInt(value.substring(4, 6), 16)
            "$r, $g, $b"
        }
        else -> null
    }
}
