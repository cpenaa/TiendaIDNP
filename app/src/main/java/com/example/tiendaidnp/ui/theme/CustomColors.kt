package com.example.tiendaidnp.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class CustomColors(
    val primary10: Color,
    val primary20: Color,
    val primary30: Color,
    val primary40: Color,
    val primary50: Color,
    val primary60: Color,
    val primary70: Color,
    val primary80: Color,
    val primary90: Color,
    val primary100: Color,
    val neutral10: Color,
    val neutral20: Color,
    val neutral30: Color,
    val neutral40: Color,
    val neutral50: Color,
    val neutral60: Color,
    val neutral70: Color,
    val neutral80: Color,
    val neutral90: Color,
    val neutral100: Color,
    val white: Color,
    val black: Color
    )

// Paletas personalizadas para los temas claro y oscuro

val CustomColorsLight = CustomColors(
    primary10 = Pink10,
    primary20 = Pink20,
    primary30 = Pink30,
    primary40 = Pink40,
    primary50 = Pink50,
    primary60 = Pink60,
    primary70 = Pink70,
    primary80 = Pink80,
    primary90 = Pink90,
    primary100 = Pink100,
    neutral10 = Neutral10,
    neutral20 = Neutral20,
    neutral30 = Neutral30,
    neutral40 = Neutral40,
    neutral50 = Neutral50,
    neutral60 = Neutral60,
    neutral70 = Neutral70,
    neutral80 = Neutral80,
    neutral90 = Neutral90,
    neutral100 = Neutral100,
    white = white,
    black = black,
)

val CustomColorsDark = CustomColors(
    primary10 = Pink100,
    primary20 = Pink90,
    primary30 = Pink80,
    primary40 = Pink70,
    primary50 = Pink60,
    primary60 = Pink50,
    primary70 = Pink40,
    primary80 = Pink30,
    primary90 = Pink20,
    primary100 = Pink10,
    neutral10 = Neutral100,
    neutral20 = Neutral90,
    neutral30 = Neutral80,
    neutral40 = Neutral70,
    neutral50 = Neutral60,
    neutral60 = Neutral50,
    neutral70 = Neutral40,
    neutral80 = Neutral30,
    neutral90 = Neutral20,
    neutral100 = Neutral10,
    white = black,
    black = white
)

val LocalCustomColors = staticCompositionLocalOf<CustomColors> {
    error("No custom colors provided")
}
