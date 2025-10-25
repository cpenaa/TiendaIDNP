package com.example.tiendaidnp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.tiendaidnp.data.repository.ProductRepository
import com.example.tiendaidnp.data.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProductsViewModel : ViewModel() {
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> get() = _products

    init {
        loadProducts()
    }

    private fun loadProducts() {
        _products.value = ProductRepository.getProducts()
    }
}
