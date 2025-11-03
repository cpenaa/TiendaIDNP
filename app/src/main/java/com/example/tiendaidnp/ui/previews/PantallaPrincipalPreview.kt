package com.example.tiendaidnp.ui.previews

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.tiendaidnp.ui.screens.OrientationAwareScreen
import com.example.tiendaidnp.ui.screens.PantallaPrincipal
import com.example.tiendaidnp.ui.screens.ResponsiveScreen
import com.example.tiendaidnp.ui.theme.TuAppTheme

@Preview(
    name = "Phone - Light",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    widthDp = 360,
    heightDp = 640,
    showBackground = true
)
//@Composable
//fun PreviewPantallaPrincipalLight() {
//    TuAppTheme(darkTheme = false) {
//        PantallaPrincipal(navController = TODO())
//    }
//}
//@Preview(
//    name = "Phone - Dark",
//    uiMode = Configuration.UI_MODE_NIGHT_YES,
//    widthDp = 360,
//    heightDp = 640,
//    showBackground = true
//)
//@Composable
//fun PreviewPantallaPrincipalDark() {
//    TuAppTheme(darkTheme = true) {
//        PantallaPrincipal(navController = TODO())
//    }
//}
//@Preview(
//    name = "Tablet - Dark",
//    uiMode = Configuration.UI_MODE_NIGHT_YES,
//    widthDp = 800,
//    heightDp = 1280,
//    showBackground = true
//)
//@Composable
//fun PreviewPantallaPrincipalTabletDark() {
//    TuAppTheme(darkTheme = true) {
//        PantallaPrincipal(navController = TODO())
//    }
//}
//@Preview(
//    name = "Large Font",
//    uiMode = Configuration.UI_MODE_NIGHT_NO,
//    widthDp = 360,
//    heightDp = 640,
//    fontScale = 1.3f,
//    showBackground = true
//)
//@Composable
//fun PreviewPantallaPrincipalLargeFont() {
//    TuAppTheme(darkTheme = false) {
//        PantallaPrincipal(navController = TODO())
//    }
//}

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