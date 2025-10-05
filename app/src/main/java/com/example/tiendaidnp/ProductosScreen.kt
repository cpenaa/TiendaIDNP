package com.example.tiendaidnp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import com.example.tiendaidnp.model.Producto

@Composable
fun ProductosScreen() {
    val productos = listOf(
        Producto("Producto 1", R.drawable.product_1, 25.50, enOferta = true),
        Producto("Producto 2", R.drawable.product_2, 40.00, enOferta = false),
        Producto("Producto 3", R.drawable.product_3, 15.99, enOferta = true)
    )

    Scaffold(
        topBar = { ProductosTopBar() },
        bottomBar = { ProductosBottomBar() }
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(productos) { producto ->
                ProductoItem(producto)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductosTopBar() {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.Top
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Perfil",
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = "Hola,",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Gabriela",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    )
}

@Composable
fun ProductosBottomBar() {
    NavigationBar {
        NavigationBarItem(
            selected = true,
            onClick = { /* TODO */ },
            icon = { Icon(painterResource(R.drawable.home), contentDescription = "Inicio") },
            label = { Text("Inicio") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { /* TODO */ },
            icon = { Icon(painterResource(R.drawable.favorite), contentDescription = "Favoritos") },
            label = { Text("Favoritos") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { /* TODO */ },
            icon = { Icon(painterResource(R.drawable.cart), contentDescription = "Carrito") },
            label = { Text("Carrito") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { /* TODO */ },
            icon = { Icon(painterResource(R.drawable.config), contentDescription = "Ajustes") },
            label = { Text("Ajustes") }
        )
    }
}

@Composable
fun ProductoItem(producto: Producto) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.tertiary,
                shape = RoundedCornerShape(12.dp)
            )
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.secondary),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.tertiary,
                    shape = RoundedCornerShape(12.dp)
                )
                .background(Color.White),
        ) {
            Image(
                painter = painterResource(id = producto.imagenId),
                contentDescription = producto.nombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
                contentScale = ContentScale.Crop
            )
            if (producto.enOferta) {
                Text(
                    text = "En Oferta",
                    color = Color.White,
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.tertiary, shape = RoundedCornerShape(6.dp))
                        .padding(horizontal = 6.dp, vertical = 4.dp)
                        .align(Alignment.TopStart)
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = producto.nombre,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.tertiary
        )
        Text(
            text = "S/ ${producto.precio}",
            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}

