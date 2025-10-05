package com.example.tiendaidnp.screens.pantallaPrincipal

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.tiendaidnp.OrientationAwareScreen
import com.example.tiendaidnp.PantallaPrincipal
import com.example.tiendaidnp.ResponsiveScreen
import com.example.tiendaidnp.ui.theme.TuAppTheme

@Preview(
    name = "Phone - Light",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    widthDp = 360,
    heightDp = 640,
    showBackground = true
)
@Composable
fun PreviewPantallaPrincipalLight() {
    TuAppTheme(darkTheme = false) {
        PantallaPrincipal(onNavigate = { })
    }
}
@Preview(
    name = "Phone - Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    widthDp = 360,
    heightDp = 640,
    showBackground = true
)
@Composable
fun PreviewPantallaPrincipalDark() {
    TuAppTheme(darkTheme = true) {
        PantallaPrincipal(onNavigate = { })
    }
}
@Preview(
    name = "Tablet - Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    widthDp = 800,
    heightDp = 1280,
    showBackground = true
)
@Composable
fun PreviewPantallaPrincipalTabletDark() {
    TuAppTheme(darkTheme = true) {
        PantallaPrincipal(onNavigate = { })
    }
}
@Preview(
    name = "Large Font",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    widthDp = 360,
    heightDp = 640,
    fontScale = 1.3f,
    showBackground = true
)
@Composable
fun PreviewPantallaPrincipalLargeFont() {
    TuAppTheme(darkTheme = false) {
        PantallaPrincipal(onNavigate = { })
    }
}

@Preview(
    name = "Responsive - Phone",
    widthDp = 360,
    heightDp = 640,
    showBackground = true
)
@Preview(
    name = "Responsive - Tablet",
    widthDp = 800,
    heightDp = 1280,
    showBackground = true
)
@Composable
fun PreviewResponsiveScreen() {
    TuAppTheme {
        ResponsiveScreen()
    }
}

@Preview(
    name = "Portrait",
    widthDp = 360,
    heightDp = 640,
    showBackground = true
)
@Composable
fun PreviewOrientationPortrait() {
    TuAppTheme {
        OrientationAwareScreen()
    }
}

@Preview(
    name = "Landscape",
    widthDp = 640,
    heightDp = 360,
    showBackground = true
)
@Composable
fun PreviewOrientationLandscape() {
    TuAppTheme {
        OrientationAwareScreen()
    }
}