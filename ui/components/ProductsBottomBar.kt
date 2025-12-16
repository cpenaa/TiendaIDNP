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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.tiendaidnp.R
import com.example.tiendaidnp.ui.navigation.Routes
import com.example.tiendaidnp.ui.theme.AppTheme
import com.example.tiendaidnp.ui.theme.Neutral30
import com.example.tiendaidnp.ui.theme.Neutral50
import com.example.tiendaidnp.ui.theme.Pink50
import com.example.tiendaidnp.ui.theme.transparent

@Composable
fun ProductsBottomBar(navController: NavController) {
    val colors = AppTheme.customColors
    val context = LocalContext.current

    // Observa la ruta actual y usa un fallback al inicio
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: Routes.HOME

    val items = listOf(
        Routes.PRODUCTS to R.drawable.home,
        Routes.MY_ORDERS to R.drawable.favorite, // Actualizado
        Routes.CART to R.drawable.cart,
        Routes.PROFILE to R.drawable.config
    )

    val implementedRoutes = listOf(Routes.PRODUCTS, Routes.PROFILE, Routes.CART, Routes.MY_ORDERS) // Actualizado

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(colors.white)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Neutral30)
        )

        NavigationBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            containerColor = transparent,
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
                                        colors.primary20
                                    else
                                        transparent,
                                    shape = CircleShape
                                )
                                .padding(8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = iconId),
                                contentDescription = route,
                                tint = if (selected)
                                    Pink50
                                else
                                    Neutral50
                            )
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = transparent
                    )
                )
            }
        }
    }
}
