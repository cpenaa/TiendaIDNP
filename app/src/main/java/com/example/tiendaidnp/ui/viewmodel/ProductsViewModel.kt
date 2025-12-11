package com.example.tiendaidnp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.tiendaidnp.data.model.AppDatabase
import com.example.tiendaidnp.data.model.Product
import com.example.tiendaidnp.data.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class ProductsViewModel(application: Application) : AndroidViewModel(application) {
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> get() = _products

    private val productRepository: ProductRepository

    init {
        val productDao = AppDatabase.getDatabase(application).productDao()
        productRepository = ProductRepository(productDao, application)
        loadProducts()
    }

    private fun loadProducts() {
        productRepository.allProducts
            .onEach { productList ->
                _products.value = productList
            }
            .launchIn(viewModelScope)
    }

    fun getProductById(productId: Long): Flow<Product> {
        return productRepository.getProductById(productId)
    }

    fun deleteProduct(productId: Long) {
        viewModelScope.launch {
            productRepository.deleteProduct(productId)
        }
    }
}
