package com.example.tiendaidnp.ui.components.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun PrimaryButton(
    text: String,
    type: ButtonType = ButtonType.BIG,
    icon: Boolean = false,
    enabled: Boolean = true, // 👈 Nuevo parámetro
    onClick: () -> Unit
) {
    val height = when (type) {
        ButtonType.SLIM -> 40.dp
        ButtonType.BIG -> 48.dp
    }

    // 🔹 Colores del botón (activo e inactivo)
    val backgroundColor = if (enabled) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)
    }

    Button(
        onClick = onClick,
        enabled = enabled, // 👈 Controla el estado
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        modifier = Modifier
            .height(height)
            .padding(0.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (icon) {
                Image(
                    painter = painterResource(id = com.example.tiendaidnp.R.drawable.right_arrow),
                    contentDescription = "Flecha",
                    colorFilter = ColorFilter.tint(Color.White.copy(alpha = if (enabled) 1f else 0.6f))
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = text,
                style = MaterialTheme.typography.displayLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White.copy(alpha = if (enabled) 1f else 0.6f)
            )
        }
    }
}
