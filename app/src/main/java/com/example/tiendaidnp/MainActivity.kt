package com.example.tiendaidnp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.tiendaidnp.ui.navigation.AppNavigation
import com.example.tiendaidnp.theme.TuAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TuAppTheme {
                AppNavigation()
            }
        }
    }
}
