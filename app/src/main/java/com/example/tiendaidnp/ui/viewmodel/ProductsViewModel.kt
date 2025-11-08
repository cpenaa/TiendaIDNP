package com.example.tiendaidnp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.tiendaidnp.data.model.Product
import com.example.tiendaidnp.data.repository.ProductRepository
import com.example.tiendaidnp.data.repository.RepositoryProvider
import com.example.tiendaidnp.data.repository.StaticProductRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ProductsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ProductRepository =
        RepositoryProvider.provideProductRepository(application)

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> get() = _products

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    init {
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch {
            _isLoading.value = true

            repository.getAllProducts().collect { productList ->
                if (productList.isEmpty()) {
                    preloadStaticProducts()
                } else {
                    _products.value = productList
                    _isLoading.value = false
                }
            }
        }
    }

    private fun preloadStaticProducts() {
        viewModelScope.launch {
            val staticProducts = StaticProductRepository.getProducts()
            staticProducts.forEach { repository.upsertProduct(it) }
            _isLoading.value = false
        }
    }

    // -----------------------------------------------------------
    //  CRUD OPERATIONS
    // -----------------------------------------------------------
    fun addProduct(product: Product) {
        viewModelScope.launch {
            repository.upsertProduct(product)
        }
    }
    fun updateProduct(product: Product) {
        viewModelScope.launch {
            repository.upsertProduct(product)
        }
    }
    fun deleteProduct(productId: Long) {
        viewModelScope.launch {
            repository.deleteProduct(productId)
        }
    }
    fun filterByCategory(category: String) {
        viewModelScope.launch {
            repository.getProductsByCategory(category).collect {
                _products.value = it
            }
        }
    }
}
