package com.example.test.data.repository

import androidx.room.withTransaction
import com.example.test.data.resource.api.AppApi
import com.example.test.data.resource.database.AppDatabase
import com.example.test.domain.entity.response.LoginResponse
import com.example.test.domain.entity.response.ProductTable
import com.example.test.domain.entity.response.RegisterRequest
import com.example.test.domain.entity.response.RegisterResponse
import com.example.test.utils.Resource
import com.example.test.utils.networkBoundResource
import kotlinx.coroutines.flow.Flow

class AppRepository(private val api: AppApi, private val db : AppDatabase, private val authenticationDataSource: AuthenticationDataSource) {

    private val productDao = db.productDao()

    suspend fun register(registerRequest: RegisterRequest): Flow<Resource<RegisterResponse>> = authenticationDataSource.register(registerRequest)
    suspend fun login(email : String, password : String ): Flow<Resource<LoginResponse>> = authenticationDataSource.login(email, password)

    suspend fun getAllProducts(): Flow<Resource<List<ProductTable>>> =
        networkBoundResource(
            query = {
                productDao.getAllProducts()
            },
            fetch = {
                api.getAllProducts()
            },
            saveFetchResult = { profile ->
                db.withTransaction {
                    productDao.deleteAllProducts()
                    profile?.let { productDao.insertProducts(it) }
                }
            }
        )

}