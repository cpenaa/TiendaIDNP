package com.example.tiendaidnp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.tiendaidnp.R
import com.example.tiendaidnp.data.model.UserProfile
import com.example.tiendaidnp.data.repository.UserPreferencesRepository
import com.example.tiendaidnp.ui.theme.AppTheme
import com.example.tiendaidnp.ui.theme.Neutral50
import com.example.tiendaidnp.ui.theme.Pink50

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsTopBar() {
    val colors = AppTheme.customColors
    val context = LocalContext.current
    val userRepo = remember { UserPreferencesRepository(context) }
    val userProfile by userRepo.userProfileFlow.collectAsState(
        initial = UserProfile("", "", "", "", "", "")
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Perfil",
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = "Hola,",
                    style = MaterialTheme.typography.bodyMedium.copy(colors.primary80)
                )
                Text(
                    text = userProfile.name,
                    style = MaterialTheme.typography.titleMedium.copy(colors.primary80)
                )
            }
        }
        Box(
            modifier = Modifier
                .size(40.dp)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.alerts),
                contentDescription = "Alerts",
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

