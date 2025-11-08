package com.example.tiendaidnp.data.repository

import android.content.Context
import com.example.tiendaidnp.data.database.DatabaseProvider

object RepositoryProvider {

    fun provideProductRepository(context: Context): ProductRepository {
        val db = DatabaseProvider.getDatabase(context)
        return ProductRepository(
            productDao = db.productDao(),
            variantDao = db.variantDao()
        )
    }
}
