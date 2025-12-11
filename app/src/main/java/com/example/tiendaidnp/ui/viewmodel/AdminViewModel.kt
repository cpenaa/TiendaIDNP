package com.example.tiendaidnp.ui.viewmodel

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.tiendaidnp.data.model.AppDatabase
import com.example.tiendaidnp.data.model.ProductDB
import com.example.tiendaidnp.data.repository.ProductRepository
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.util.UUID

class AdminViewModel(application: Application) : AndroidViewModel(application) {
    private val productRepository: ProductRepository

    init {
        val productDao = AppDatabase.getDatabase(application).productDao()
        productRepository = ProductRepository(productDao, application)
    }

    private fun saveImageToInternalStorage(uri: Uri): String {
        val context = getApplication<Application>().applicationContext
        val inputStream = context.contentResolver.openInputStream(uri)
        val fileName = "${UUID.randomUUID()}.jpg"
        val file = File(context.filesDir, fileName)
        val outputStream = FileOutputStream(file)
        inputStream?.copyTo(outputStream)
        inputStream?.close()
        outputStream.close()
        return file.absolutePath
    }

    fun insertProduct(
        name: String,
        color: String,
        imageUri: String,
        quantity: String,
        price: String,
        size: String,
        category: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        if (imageUri.isEmpty()) {
            onError("Por favor, selecciona una imagen.")
            return
        }

        try {
            val newImagePath = saveImageToInternalStorage(Uri.parse(imageUri))

            val product = ProductDB(
                name = name,
                color = color,
                imageUri = newImagePath,
                quantity = quantity.toInt(),
                price = price.toDouble(),
                size = size,
                category = category
            )
            viewModelScope.launch {
                productRepository.insertProduct(product)
                onSuccess()
            }
        } catch (e: NumberFormatException) {
            onError("Por favor, ingresa un número válido para cantidad y precio.")
        } catch (e: Exception) {
            onError("Error al guardar la imagen: ${e.message}")
        }
    }

    fun updateProduct(
        productId: Long,
        name: String,
        color: String,
        imageUri: String,
        quantity: String,
        price: String,
        size: String,
        category: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        try {
            val newImagePath = if (imageUri.startsWith("content://")) {
                saveImageToInternalStorage(Uri.parse(imageUri))
            } else {
                imageUri
            }

            val product = ProductDB(
                id = productId,
                name = name,
                color = color,
                imageUri = newImagePath,
                quantity = quantity.toInt(),
                price = price.toDouble(),
                size = size,
                category = category
            )
            viewModelScope.launch {
                productRepository.updateProduct(product)
                onSuccess()
            }
        } catch (e: NumberFormatException) {
            onError("Por favor, ingresa un número válido para cantidad y precio.")
        } catch (e: Exception) {
            onError("Error al guardar la imagen: ${e.message}")
        }
    }
}