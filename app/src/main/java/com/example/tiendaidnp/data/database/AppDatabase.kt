package com.example.tiendaidnp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.tiendaidnp.data.dao.*
import com.example.tiendaidnp.data.model.*
import com.example.tiendaidnp.data.utils.Converters

@Database(
    entities = [
        Product::class,
        Variant::class,
        CartItem::class,
        AppOrder::class,
        OrderItem::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun variantDao(): VariantDao
    abstract fun cartDao(): CartDao
    abstract fun orderDao(): OrderDao
    abstract fun orderItemDao(): OrderItemDao
}
