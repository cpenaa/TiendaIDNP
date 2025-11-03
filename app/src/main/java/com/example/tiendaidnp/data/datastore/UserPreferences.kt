package com.example.tiendaidnp.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.firstOrNull
import androidx.datastore.preferences.preferencesDataStore

// Nombre del archivo DataStore
private const val USER_PREFERENCES_NAME = "user_preferences"

// Extensión para acceder al DataStore desde un contexto
val Context.userDataStore by preferencesDataStore(name = USER_PREFERENCES_NAME)

// Definición de las claves para los campos del perfil
object UserPreferencesKeys {
    val NAME = stringPreferencesKey("user_name")
    val LASTNAME = stringPreferencesKey("user_lastname")
    val EMAIL = stringPreferencesKey("user_email")
    val PHONE = stringPreferencesKey("user_phone")
    val ADDRESS = stringPreferencesKey("user_address")
    val PASSWORD = stringPreferencesKey("user_password")

    // 🌓 Nueva clave para el modo de tema
    val THEME_MODE = stringPreferencesKey("theme_mode")
}

enum class ThemeMode {
    LIGHT, DARK, SYSTEM
}

// 🧩 Funciones para guardar y leer el modo de tema
object ThemePreferences {

    suspend fun saveThemeMode(context: Context, themeMode: ThemeMode) {
        context.userDataStore.edit { prefs ->
            prefs[UserPreferencesKeys.THEME_MODE] = themeMode.name
        }
    }

    fun getThemeMode(context: Context): Flow<ThemeMode> {
        return context.userDataStore.data.map { prefs ->
            val mode = prefs[UserPreferencesKeys.THEME_MODE]
            try {
                ThemeMode.valueOf(mode ?: ThemeMode.SYSTEM.name)
            } catch (_: IllegalArgumentException) {
                ThemeMode.SYSTEM
            }
        }
    }

    // ⚙️ (Opcional) Obtener el modo actual de forma directa (no reactiva)
    suspend fun getCurrentThemeMode(context: Context): ThemeMode {
        val prefs = context.userDataStore.data.firstOrNull()
        val mode = prefs?.get(UserPreferencesKeys.THEME_MODE)
        return try {
            ThemeMode.valueOf(mode ?: ThemeMode.SYSTEM.name)
        } catch (_: IllegalArgumentException) {
            ThemeMode.SYSTEM
        }
    }
}
