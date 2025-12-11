package com.example.tiendaidnp.data.repository

<<<<<<< HEAD
import android.content.Context
import com.example.tiendaidnp.data.model.Product
import com.example.tiendaidnp.data.model.ProductDao
import com.example.tiendaidnp.data.model.ProductDB
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
=======
import com.example.tiendaidnp.R
import com.example.tiendaidnp.data.model.Product

object ProductRepository {
    fun getProducts(): List<Product> = listOf(
        // Pantalones
        Product(
            sku = "PANT-001",
            name = "Pantalón Marrón",
            category = "Pantalones",
            brand = "UrbanStyle",
            price = 90.0,
            oldPrice = 120.0,
            inOffer = true,
            stock = 14,
            imagenId = R.drawable.prod_pants_1,
            availableSizes = listOf("S", "M", "L", "XL")
        ),
        Product(
            sku = "PANT-002",
            name = "Jogger Jean",
            category = "Pantalones",
            brand = "DenimFlex",
            price = 110.0,
            oldPrice = null,
            inOffer = false,
            stock = 20,
            imagenId = R.drawable.prod_pants_2,
            availableSizes = listOf("M", "L", "XL")
        ),
        Product(
            sku = "PANT-003",
            name = "Pantalón Jean Clásico",
            category = "Pantalones",
            brand = "DenimFlex",
            price = 130.0,
            oldPrice = 160.0,
            inOffer = true,
            stock = 18,
            imagenId = R.drawable.prod_pants_3,
            availableSizes = listOf("S", "M", "L")
        ),
        Product(
            sku = "PANT-004",
            name = "Pantalón Amarillo",
            category = "Pantalones",
            brand = "ColorFit",
            price = 95.0,
            oldPrice = null,
            inOffer = false,
            stock = 10,
            imagenId = R.drawable.prod_pants_4,
            availableSizes = listOf("S", "M")
        ),
        Product(
            sku = "PANT-005",
            name = "Pantalón Dril",
            category = "Pantalones",
            brand = "UrbanStyle",
            price = 140.0,
            oldPrice = 180.0,
            inOffer = true,
            stock = 8,
            imagenId = R.drawable.prod_pants_5,
            availableSizes = listOf("M", "L", "XL")
        ),

        // Blusas
        Product(
            sku = "BLOU-001",
            name = "Blusa Blanca Clásica",
            category = "Blusas",
            brand = "SoftWear",
            price = 60.0,
            oldPrice = null,
            inOffer = false,
            stock = 25,
            imagenId = R.drawable.prod_blouse_1,
            availableSizes = listOf("S", "M", "L")
        ),
        Product(
            sku = "BLOU-002",
            name = "Blusa de Seda",
            category = "Blusas",
            brand = "ElegantLine",
            price = 75.0,
            oldPrice = 90.0,
            inOffer = true,
            stock = 12,
            imagenId = R.drawable.prod_blouse_2,
            availableSizes = listOf("S", "M")
        ),
        Product(
            sku = "BLOU-003",
            name = "Blusa Azul Marina",
            category = "Blusas",
            brand = "SoftWear",
            price = 70.0,
            oldPrice = null,
            inOffer = false,
            stock = 30,
            imagenId = R.drawable.prod_blouse_3,
            availableSizes = listOf("M", "L")
        ),
        Product(
            sku = "BLOU-004",
            name = "Blusa Floral",
            category = "Blusas",
            brand = "ColorFit",
            price = 65.0,
            oldPrice = 80.0,
            inOffer = true,
            stock = 9,
            imagenId = R.drawable.prod_blouse_4,
            availableSizes = listOf("S", "M", "L")
        ),
        Product(
            sku = "BLOU-005",
            name = "Blusa Rosa Casual",
            category = "Blusas",
            brand = "UrbanStyle",
            price = 55.0,
            oldPrice = null,
            inOffer = false,
            stock = 22,
            imagenId = R.drawable.prod_blouse_5,
            availableSizes = listOf("S", "M", "L", "XL")
        ),

        // Vestidos
        Product(
            sku = "DRES-001",
            name = "Vestido Rojo Elegante",
            category = "Vestidos",
            brand = "ElegantLine",
            price = 180.0,
            oldPrice = 220.0,
            inOffer = true,
            stock = 6,
            imagenId = R.drawable.prod_dress_1,
            availableSizes = listOf("S", "M", "L")
        ),
        Product(
            sku = "DRES-002",
            name = "Vestido Azul Marino",
            category = "Vestidos",
            brand = "SoftWear",
            price = 160.0,
            oldPrice = null,
            inOffer = false,
            stock = 10,
            imagenId = R.drawable.prod_dress_2,
            availableSizes = listOf("M", "L", "XL")
        ),
        Product(
            sku = "DRES-003",
            name = "Vestido Floral de Verano",
            category = "Vestidos",
            brand = "ColorFit",
            price = 150.0,
            oldPrice = 180.0,
            inOffer = true,
            stock = 8,
            imagenId = R.drawable.prod_dress_3,
            availableSizes = listOf("S", "M", "L")
        ),
        Product(
            sku = "DRES-004",
            name = "Vestido Blanco Casual",
            category = "Vestidos",
            brand = "UrbanStyle",
            price = 140.0,
            oldPrice = null,
            inOffer = false,
            stock = 15,
            imagenId = R.drawable.prod_dress_4,
            availableSizes = listOf("S", "M", "L", "XL")
        ),
        Product(
            sku = "DRES-005",
            name = "Vestido Negro Nocturno",
            category = "Vestidos",
            brand = "ElegantLine",
            price = 190.0,
            oldPrice = 230.0,
            inOffer = true,
            stock = 5,
            imagenId = R.drawable.prod_dress_5,
            availableSizes = listOf("S", "M")
        ),

        // Casacas
        Product(
            sku = "JACK-001",
            name = "Chaqueta Cuero Negra",
            category = "Casacas",
            brand = "UrbanStyle",
            price = 220.0,
            oldPrice = 260.0,
            inOffer = true,
            stock = 7,
            imagenId = R.drawable.prod_jacket_1,
            availableSizes = listOf("M", "L")
        ),
        Product(
            sku = "JACK-002",
            name = "Chaqueta Jean",
            category = "Casacas",
            brand = "DenimFlex",
            price = 180.0,
            oldPrice = null,
            inOffer = false,
            stock = 12,
            imagenId = R.drawable.prod_jacket_2,
            availableSizes = listOf("S", "M", "L", "XL")
        ),
        Product(
            sku = "JACK-003",
            name = "Chaqueta Marrón Casual",
            category = "Casacas",
            brand = "ColorFit",
            price = 200.0,
            oldPrice = 240.0,
            inOffer = true,
            stock = 10,
            imagenId = R.drawable.prod_jacket_3,
            availableSizes = listOf("M", "L")
        ),
        Product(
            sku = "JACK-004",
            name = "Chaqueta Impermeable",
            category = "Casacas",
            brand = "UrbanStyle",
            price = 150.0,
            oldPrice = null,
            inOffer = false,
            stock = 18,
            imagenId = R.drawable.prod_jacket_4,
            availableSizes = listOf("S", "M", "L", "XL")
        ),
        Product(
            sku = "JACK-005",
            name = "Chaqueta de Lana",
            category = "Casacas",
            brand = "SoftWear",
            price = 170.0,
            oldPrice = 200.0,
            inOffer = true,
            stock = 9,
            imagenId = R.drawable.prod_jacket_5,
            availableSizes = listOf("S", "M", "L")
        ),

        // Zapatillas
        Product(
            sku = "SNKR-001",
            name = "Zapatillas Blancas Clásicas",
            category = "Zapatillas",
            brand = "StepPro",
            price = 130.0,
            oldPrice = 150.0,
            inOffer = true,
            stock = 20,
            imagenId = R.drawable.prod_sneaker_1,
            availableSizes = listOf("38", "39", "40", "41", "42", "43")
        ),
        Product(
            sku = "SNKR-002",
            name = "Zapatillas Negras Urbanas",
            category = "Zapatillas",
            brand = "UrbanStyle",
            price = 140.0,
            oldPrice = null,
            inOffer = false,
            stock = 16,
            imagenId = R.drawable.prod_sneaker_2,
            availableSizes = listOf("39", "40", "41", "42")
        ),
        Product(
            sku = "SNKR-003",
            name = "Zapatillas Running",
            category = "Zapatillas",
            brand = "StepPro",
            price = 160.0,
            oldPrice = 190.0,
            inOffer = true,
            stock = 14,
            imagenId = R.drawable.prod_sneaker_3,
            availableSizes = listOf("40", "41", "42", "43", "44")
        ),
        Product(
            sku = "SNKR-004",
            name = "Zapatillas Azul Deportivo",
            category = "Zapatillas",
            brand = "ColorFit",
            price = 135.0,
            oldPrice = null,
            inOffer = false,
            stock = 11,
            imagenId = R.drawable.prod_sneaker_4,
            availableSizes = listOf("38", "39", "40", "41")
        ),
        Product(
            sku = "SNKR-005",
            name = "Zapatillas Rojas Fashion",
            category = "Zapatillas",
            brand = "UrbanStyle",
            price = 150.0,
            oldPrice = 180.0,
            inOffer = true,
            stock = 13,
            imagenId = R.drawable.prod_sneaker_5,
            availableSizes = listOf("39", "40", "41", "42", "43")
        )
    )

>>>>>>> 5bf2d1506f5e2a78fb77fb3db14961a213ced41e
}