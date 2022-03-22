package com.example.test.data.resource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.test.domain.entity.response.ProductTable

@Database(entities = [ProductTable::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}