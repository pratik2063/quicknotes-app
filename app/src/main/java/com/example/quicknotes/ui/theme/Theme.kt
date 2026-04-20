package com.example.quicknotes.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = Green40,
    secondary = Mint40,
    tertiary = GreenGrey40
)

private val DarkColors = darkColorScheme(
    primary = Green80,
    secondary = Mint80,
    tertiary = GreenGrey80
)

@Composable
fun QuickNotesTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = Typography,
        content = content
    )
}
