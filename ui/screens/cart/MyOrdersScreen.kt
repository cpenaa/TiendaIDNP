package com.example.tiendaidnp.ui.screens.cart

import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tiendaidnp.data.model.Order
import com.example.tiendaidnp.ui.components.ProductsBottomBar
import com.example.tiendaidnp.ui.components.ScreenTitle
import com.example.tiendaidnp.ui.viewmodel.OrderViewModel
import com.example.tiendaidnp.ui.viewmodel.OrderViewModelFactory

@Composable
fun MyOrdersScreen(navController: NavController) {
    val application = LocalContext.current.applicationContext as Application
    val orderViewModel: OrderViewModel = viewModel(factory = OrderViewModelFactory(application))
    val orders by orderViewModel.orders.collectAsState()

    LaunchedEffect(Unit) {
        // Usamos un userId fijo (1L) por ahora
        orderViewModel.loadOrdersByUser(1L)
    }

    Scaffold(
        bottomBar = { ProductsBottomBar(navController = navController) }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            ScreenTitle(title = "Mis Pedidos")
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(orders) { order ->
                    MyOrderItem(order = order)
                }
            }
        }
    }
}

@Composable
fun MyOrderItem(order: Order) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Pedido #${order.id}")
        Text(text = "Estado: ${order.status}")
    }
}