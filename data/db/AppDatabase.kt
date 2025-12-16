package com.example.tiendaidnp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.tiendaidnp.data.db.entities.Cart
import com.example.tiendaidnp.data.db.dao.CartDao
import com.example.tiendaidnp.data.db.entities.CartItem
import com.example.tiendaidnp.data.model.Order
import com.example.tiendaidnp.data.db.dao.OrderDao
import com.example.tiendaidnp.data.db.entities.OrderItem
import com.example.tiendaidnp.data.db.entities.ProductDB
import com.example.tiendaidnp.data.db.dao.ProductDao

@Database(entities = [ProductDB::class, Cart::class, CartItem::class, Order::class, OrderItem::class], version = 5)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun cartDao(): CartDao
    abstract fun orderDao(): OrderDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}