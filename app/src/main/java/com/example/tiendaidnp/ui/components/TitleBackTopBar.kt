package com.example.tiendaidnp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.tiendaidnp.ui.theme.AppTheme

@Composable
fun TitleBackTopBar(
    title: String,
    onBackClick: () -> Unit
) {
    val colors = AppTheme.customColors

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .background(colors.white)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Icono de regreso
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(colors.primary30)
                .clickable { onBackClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Volver",
                tint = colors.white
            )
        }

        // Título centrado
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color = colors.primary80,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )

        // Espacio vacío a la derecha para balancear el icono
        Spacer(modifier = Modifier.width(36.dp))
    }
}
