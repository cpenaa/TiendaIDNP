package com.example.tiendaidnp.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Pink40,
    secondary = Pink80,
    tertiary = Pink10,
    background = black,
    onBackground = Pink20,
    surface = Neutral50,
    surfaceVariant = Pink50,
    outline = Neutral30,
    primaryContainer = Pink20,
    outlineVariant = white
)

private val LightColorScheme = lightColorScheme(
    primary = Pink40,
    secondary = Pink10,
    tertiary = Pink80,
    background = white,
    onBackground = Pink80,
    surface = Neutral50,
    surfaceVariant = Pink50,
    outline = Neutral30,
    primaryContainer = Pink20,
    outlineVariant = black
)

@Composable
fun TuAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

@Composable
fun FinalProjectIDNPTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}