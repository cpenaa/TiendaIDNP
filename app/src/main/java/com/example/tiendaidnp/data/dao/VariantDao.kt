package com.example.tiendaidnp.data.dao

import androidx.room.*
import com.example.tiendaidnp.data.model.Variant
import kotlinx.coroutines.flow.Flow

@Dao
interface VariantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVariant(variant: Variant)

    @Query("DELETE FROM variants WHERE id = :variantId")
    suspend fun deleteVariantById(variantId: Long)

    @Query("SELECT * FROM variants WHERE productId = :productId")
    fun getVariantsByProductId(productId: Long): Flow<List<Variant>>
}
