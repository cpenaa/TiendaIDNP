package com.example.tiendaidnp.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tiendaidnp.data.datastore.ThemeMode
import com.example.tiendaidnp.data.datastore.ThemePreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ThemeViewModel(private val context: Context) : ViewModel() {

    // Estado interno mutable
    private val _themeMode = MutableStateFlow(ThemeMode.SYSTEM)
    val themeMode: StateFlow<ThemeMode> = _themeMode.asStateFlow()

    init {
        // Cargar el tema al iniciar la app
        viewModelScope.launch {
            ThemePreferences.getThemeMode(context).collect { mode ->
                _themeMode.value = mode
            }
        }
    }

    // Función para actualizar el tema y guardarlo en DataStore
    fun updateTheme(mode: ThemeMode) {
        viewModelScope.launch {
            ThemePreferences.saveThemeMode(context, mode)
        }
    }
}
