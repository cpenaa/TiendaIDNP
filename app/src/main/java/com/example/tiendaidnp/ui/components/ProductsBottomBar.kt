package com.example.tiendaidnp.ui.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.tiendaidnp.R
import com.example.tiendaidnp.ui.navigation.Routes

@Composable
fun ProductsBottomBar(navController: NavController) {
    val context = LocalContext.current

    // Observa la ruta actual y usa un fallback al inicio
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: Routes.HOME

    val items = listOf(
        Routes.PRODUCTS to R.drawable.home,
        "favoritos" to R.drawable.favorite,
        "carrito" to R.drawable.cart,
        Routes.PROFILE to R.drawable.config
    )

    val implementedRoutes = listOf(Routes.PRODUCTS, Routes.PROFILE)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(MaterialTheme.colorScheme.outline)
        )

        NavigationBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            containerColor = Color.Transparent,
            tonalElevation = 0.dp
        ) {
            items.forEach { (route, iconId) ->
                val selected = currentRoute == route

                NavigationBarItem(
                    selected = selected,
                    onClick = {
                        if (implementedRoutes.contains(route)) {
                            navController.navigate(route) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        } else {
                            Toast.makeText(context, "Pantalla en desarrollo", Toast.LENGTH_SHORT).show()
                        }
                    },
                    icon = {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(
                                    color = if (selected)
                                        MaterialTheme.colorScheme.primaryContainer
                                    else
                                        Color.Transparent,
                                    shape = CircleShape
                                )
                                .padding(8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = iconId),
                                contentDescription = route,
                                tint = if (selected)
                                    MaterialTheme.colorScheme.surfaceVariant
                                else
                                    MaterialTheme.colorScheme.surface
                            )
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.Transparent
                    )
                )
            }
        }
    }
}
