package com.example.tiendaidnp.ui.screens

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedCircleScreen() {
    var targetRadius by remember { mutableStateOf(80f) }

    val minRadius = 40f
    val maxRadius = 200f

    val animatedRadius by animateFloatAsState(
        targetValue = targetRadius,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "circleRadius"
    )

    val canIncrease = targetRadius < maxRadius
    val canDecrease = targetRadius > minRadius

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Canvas(modifier = Modifier.size(250.dp)) {
            drawCircle(
                color = Color(0xFF3F51B5),
                radius = animatedRadius
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = {
                    if (canIncrease) targetRadius += 20f
                },
                enabled = canIncrease
            ) {
                Text("Aumentar")
            }

            Button(
                onClick = {
                    if (canDecrease) targetRadius -= 20f
                },
                enabled = canDecrease
            ) {
                Text("Disminuir")
            }
        }
    }
}



