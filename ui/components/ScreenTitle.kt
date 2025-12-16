package com.example.tiendaidnp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.tiendaidnp.ui.theme.AppTheme
import com.example.tiendaidnp.ui.theme.Neutral30

@Composable
fun ScreenTitle(
    title: String,
) {
    val colors = AppTheme.customColors
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Neutral30)
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge.copy(colors.primary80),
            fontWeight = FontWeight.Bold,
        )

    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Neutral30)
    )
}