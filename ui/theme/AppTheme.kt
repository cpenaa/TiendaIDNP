package com.example.tiendaidnp.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable

object AppTheme {

    // 🎨 Acceso a los colores personalizados
    val customColors: CustomColors
        @Composable
        @ReadOnlyComposable
        get() = LocalCustomColors.current

    // 🧩 Acceso al esquema de colores de Material (por si quieres ambos)
    val materialColors: androidx.compose.material3.ColorScheme
        @Composable
        @ReadOnlyComposable
        get() = androidx.compose.material3.MaterialTheme.colorScheme

    // 🔤 Acceso a la tipografía global
    val typography: androidx.compose.material3.Typography
        @Composable
        @ReadOnlyComposable
        get() = androidx.compose.material3.MaterialTheme.typography
}
