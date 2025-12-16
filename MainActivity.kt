package com.example.tiendaidnp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.tiendaidnp.ui.navigation.AppNavigation
import com.example.tiendaidnp.ui.theme.TuAppTheme
import com.example.tiendaidnp.ui.viewmodel.ThemeViewModel

class MainActivity : ComponentActivity() {
    private lateinit var themeViewModel: ThemeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        themeViewModel = ThemeViewModel(this)
        setContent {
            val themeMode by themeViewModel.themeMode.collectAsState()
            TuAppTheme(themeMode = themeMode) {
                AppNavigation()
            }
        }
    }
}

