package com.example.tiendaidnp.data.repository

import android.content.Context
import com.example.tiendaidnp.data.model.Product
import com.example.tiendaidnp.data.db.dao.ProductDao
import com.example.tiendaidnp.data.db.entities.ProductDB
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProductRepository(private val productDao: ProductDao, private val context: Context) {

    val allProducts: Flow<List<Product>> = productDao.getAllProducts().map {
        it.map { productDB ->
            Product(
                id = productDB.id,
                name = productDB.name,
                price = productDB.price,
                imageUri = productDB.imageUri,
                sku = "",
                category = productDB.category,
                brand = "",
                oldPrice = null,
                inOffer = false,
                stock = productDB.quantity,
                availableSizes = listOf(productDB.size)
            )
        }
    }

    fun getProductById(productId: Long): Flow<Product> {
        return productDao.getProductById(productId).map {
            Product(
                id = it.id,
                name = it.name,
                price = it.price,
                imageUri = it.imageUri,
                sku = "",
                category = it.category,
                brand = "",
                oldPrice = null,
                inOffer = false,
                stock = it.quantity,
                availableSizes = listOf(it.size)
            )
        }
    }

    suspend fun insertProduct(product: ProductDB) {
        productDao.insertProduct(product)
    }

    suspend fun updateProduct(product: ProductDB) {
        productDao.updateProduct(product)
    }

    suspend fun deleteProduct(productId: Long) {
        productDao.deleteProductById(productId)
    }
}