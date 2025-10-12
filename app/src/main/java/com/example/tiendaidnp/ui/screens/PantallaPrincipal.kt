package com.example.tiendaidnp.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tiendaidnp.R
import com.example.tiendaidnp.ui.components.buttons.PrimaryButton

@Composable
fun PantallaPrincipal(
    onNavigate: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "GamarraAQP",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.tertiary,
            modifier = Modifier
                .padding(8.dp)
        )
        Text(
            text = "Las mejores prendas a mejor precio",
            fontSize = 16.sp
        )
        Image(
            painter = painterResource(id = R.drawable.main_image),
            contentDescription = "Imagen de portada",
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 16.dp),
            contentScale = ContentScale.FillWidth
        )
        PrimaryButton(
            text = "Empezar",
            icon = true,
            onClick = { onNavigate("productos") }
        )
//        Button(onClick = { onNavigate("productos") }) {
//            Row(
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.right_arrow),
//                    contentDescription = "Flecha"
//                )
//                Spacer(modifier = Modifier.width(8.dp))
//                Text(
//                    text = "Empezar",
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = Color(0xFFFFFFFF),
//                    modifier = Modifier
//                        .padding(4.dp)
//                )
//            }
//        }
    }
}

@Composable
fun ResponsiveScreen() {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp

    if (screenWidth < 600) {
        // teléfono
        PantallaPrincipal(onNavigate = { })
    } else {
        // tablet
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PantallaPrincipal(onNavigate = { })
            Text("Vista previa del carrito")
        }
    }
}

@Composable
fun OrientationAwareScreen() {
    val configuration = LocalConfiguration.current
    val orientation = configuration.orientation

    if (orientation == Configuration.ORIENTATION_PORTRAIT) {
        Column {
            PantallaPrincipal(onNavigate = { })
        }
    } else {
        Row {
            PantallaPrincipal(onNavigate = { })
        }
    }
}