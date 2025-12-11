package com.example.tiendaidnp.ui.viewmodel

<<<<<<< HEAD
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
=======
import androidx.lifecycle.ViewModel
import com.example.tiendaidnp.data.repository.ProductRepository
import com.example.tiendaidnp.data.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProductsViewModel : ViewModel() {
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> get() = _products

    init {
>>>>>>> 5bf2d1506f5e2a78fb77fb3db14961a213ced41e
        loadProducts()
    }

    private fun loadProducts() {
<<<<<<< HEAD
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
=======
        _products.value = ProductRepository.getProducts()
>>>>>>> 5bf2d1506f5e2a78fb77fb3db14961a213ced41e
    }
}
