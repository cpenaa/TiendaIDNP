package com.example.tiendaidnp.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext

// Colores personalizados que ya definimos
import com.example.tiendaidnp.ui.theme.CustomColorsDark
import com.example.tiendaidnp.ui.theme.CustomColorsLight
import com.example.tiendaidnp.ui.theme.LocalCustomColors

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
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    // Elegir esquema de colores base
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    // 🎨 Seleccionar la paleta personalizada correspondiente
    val customColors = if (darkTheme) CustomColorsDark else CustomColorsLight

    // 🧩 Proveer los custom colors al árbol de composición
    CompositionLocalProvider(LocalCustomColors provides customColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}
