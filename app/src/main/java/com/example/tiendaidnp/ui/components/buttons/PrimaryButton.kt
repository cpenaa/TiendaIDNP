package com.example.tiendaidnp.ui.components.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun PrimaryButton(
    text: String,
    type: ButtonType = ButtonType.BIG,
    icon: Boolean = false,
    onClick: () -> Unit
) {
    val height = when (type) {
        ButtonType.SLIM -> 40.dp
        ButtonType.BIG -> 48.dp
    }
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(height)
            .padding(0.dp)
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (icon) {
                Image(
                    painter = painterResource(
                        id = com.example.tiendaidnp.R.drawable.right_arrow
                    ),
                    contentDescription = "Flecha"
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = text,
                style = MaterialTheme.typography.displayLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}
