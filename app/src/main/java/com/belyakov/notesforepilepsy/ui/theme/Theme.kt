package com.belyakov.notesforepilepsy.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.belyakov.ui.theme.BackgroundColor
import com.belyakov.ui.theme.PrimaryDarAppColor
import com.belyakov.ui.theme.PrimaryLightAppColor
import com.belyakov.ui.theme.PrimaryTextColor

private val DarkColorPalette = darkColors(
    primary = PrimaryDarAppColor,
    primaryVariant = BackgroundColor,
    secondary = PrimaryTextColor
)

private val LightColorPalette = lightColors(
    primary = PrimaryLightAppColor,
    primaryVariant = BackgroundColor,
    secondary = PrimaryTextColor
)

@Composable
fun NotesForEpilepsyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}