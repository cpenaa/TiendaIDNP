package com.example.tiendaidnp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.tiendaidnp.data.model.Product
import com.example.tiendaidnp.ui.theme.AppTheme
import com.example.tiendaidnp.ui.theme.Neutral50
import com.example.tiendaidnp.ui.theme.black
import com.example.tiendaidnp.ui.theme.white

@Composable
fun ProductItem(producto: Product) {
    val colors = AppTheme.customColors
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = colors.neutral50,
                shape = RoundedCornerShape(12.dp)
            )
            .clip(RoundedCornerShape(12.dp))
            .background(
                if (producto.inOffer) colors.primary30 else colors.primary10),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Imagen del producto
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(white)
                .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
        ) {
            Image(
                painter = painterResource(id = producto.imagenId),
                contentDescription = producto.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
                contentScale = ContentScale.Crop
            )

            // Etiqueta de oferta (ahora más destacada y visual)
            if (producto.inOffer) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(6.dp)
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(Color(0xFFFF7043), Color(0xFFD84315)) // degradado naranja-rojo
                            ),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "En Oferta",
                        color = white,
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Nombre del producto
        Text(
            text = producto.name,
            style = MaterialTheme.typography.bodyMedium,
            color = if (producto.inOffer) colors.primary70 else colors.primary80
        )

        // Categoría (nuevo)
//        Text(
//            text = producto.category,
//            style = MaterialTheme.typography.bodySmall,
//            color = colors.primary60
//        )

        Spacer(modifier = Modifier.height(4.dp))

        // Precio actual y anterior
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = "S/ ${producto.price}",
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                color = black
            )

            producto.oldPrice?.let { old ->
                Text(
                    text = "S/ $old",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Neutral50,
                        textDecoration = TextDecoration.LineThrough
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}