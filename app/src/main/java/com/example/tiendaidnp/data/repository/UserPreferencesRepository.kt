package com.example.tiendaidnp.data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.tiendaidnp.data.model.UserProfile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// 1️⃣ Creamos una instancia única de DataStore
private val Context.dataStore by preferencesDataStore(name = "user_prefs")

class UserPreferencesRepository(private val context: Context) {

    // 2️⃣ Definimos las claves
    companion object {
        val NAME = stringPreferencesKey("name")
        val LASTNAME = stringPreferencesKey("lastname")
        val EMAIL = stringPreferencesKey("email")
        val PHONE = stringPreferencesKey("phone")
        val ADDRESS = stringPreferencesKey("address")
        val PASSWORD = stringPreferencesKey("password")
    }

    // 3️⃣ Función para guardar datos
    suspend fun saveUserProfile(userProfile: UserProfile) {
        context.dataStore.edit { prefs ->
            prefs[NAME] = userProfile.name
            prefs[LASTNAME] = userProfile.lastname
            prefs[EMAIL] = userProfile.email
            prefs[PHONE] = userProfile.phone
            prefs[ADDRESS] = userProfile.address
            prefs[PASSWORD] = userProfile.password
        }
    }

    // 4️⃣ Función para obtener datos como flujo (Flow)
    val userProfileFlow: Flow<UserProfile> = context.dataStore.data.map { prefs ->
        UserProfile(
            name = prefs[NAME] ?: "",
            lastname = prefs[LASTNAME] ?: "",
            email = prefs[EMAIL] ?: "",
            phone = prefs[PHONE] ?: "",
            address = prefs[ADDRESS] ?: "",
            password = prefs[PASSWORD] ?: ""
        )
    }
}
