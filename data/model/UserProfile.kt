package com.example.tiendaidnp.data.model

data class UserProfile(
    val name: String = "",
    val lastname: String = "",
    val email: String = "",
    val phone: String = "",
    val address: String = "",
    val password: String = "",
    val imagenUri: String = "" // opcional, si luego agregamos una imagen
)
