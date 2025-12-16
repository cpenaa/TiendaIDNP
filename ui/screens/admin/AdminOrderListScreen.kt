package com.example.tiendaidnp.ui.screens.admin

import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
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
import com.example.tiendaidnp.data.model.Order
import com.example.tiendaidnp.data.model.OrderStatus
import com.example.tiendaidnp.ui.viewmodel.OrderViewModel
import com.example.tiendaidnp.ui.viewmodel.OrderViewModelFactory

@Composable
fun AdminOrderListScreen() {
    val application = LocalContext.current.applicationContext as Application
    val orderViewModel: OrderViewModel = viewModel(factory = OrderViewModelFactory(application))
    val orders by orderViewModel.orders.collectAsState()

    LaunchedEffect(Unit) {
        orderViewModel.loadAllOrders()
    }

    Scaffold {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp)
        ) {
            items(orders) { order ->
                OrderListItem(order = order, onUpdateStatus = orderViewModel::updateOrderStatus)
            }
        }
    }
}

@Composable
fun OrderListItem(order: Order, onUpdateStatus: (Long, OrderStatus) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(text = "Pedido #${order.id}")
            Text(text = "Usuario ID: ${order.userId}")
            Text(text = "Estado: ${order.status}")
        }
        if (order.status == OrderStatus.PENDIENTE) {
            Row {
                Button(onClick = { onUpdateStatus(order.id, OrderStatus.ACEPTADO) }) {
                    Text("Aceptar")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = { onUpdateStatus(order.id, OrderStatus.RECHAZADO) }) {
                    Text("Rechazar")
                }
            }
        }
    }
}