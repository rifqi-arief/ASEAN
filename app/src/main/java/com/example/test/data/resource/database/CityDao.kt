package com.example.test.data.resource.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.test.domain.entity.response.ProductTable
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("SELECT * FROM products")
    fun getAllProducts() : Flow<List<ProductTable>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(city: List<ProductTable>)

    @Query("DELETE FROM products")
    suspend fun deleteAllProducts()

}