package com.example.tiendaidnp.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
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
}
