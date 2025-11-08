package com.example.tiendaidnp.data.repository

import com.example.tiendaidnp.data.dao.ProductDao
import com.example.tiendaidnp.data.dao.VariantDao
import com.example.tiendaidnp.data.model.Product
import com.example.tiendaidnp.data.model.Variant
import kotlinx.coroutines.flow.Flow

class ProductRepository(
    private val productDao: ProductDao,
    private val variantDao: VariantDao
) {

    fun getAllProducts(): Flow<List<Product>> = productDao.getAllProducts()

    fun getProductById(id: Long): Flow<Product?> = productDao.getProductById(id)

    suspend fun upsertProduct(product: Product) {
        productDao.upsert(product)
    }

    suspend fun deleteProduct(id: Long) {
        productDao.deleteProductById(id)
    }

    fun getProductsByCategory(category: String): Flow<List<Product>> =
        productDao.getProductsByCategory(category)

    fun getVariantsForProduct(productId: Long): Flow<List<Variant>> =
        variantDao.getVariantsByProductId(productId)

    suspend fun insertVariant(variant: Variant) {
        variantDao.insertVariant(variant)
    }

    suspend fun deleteVariant(variantId: Long) {
        variantDao.deleteVariantById(variantId)
    }
}
