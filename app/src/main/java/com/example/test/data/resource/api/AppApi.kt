package com.example.test.data.resource.api

import com.example.test.domain.entity.response.*
import retrofit2.http.*

interface AppApi {

    @GET("api/login")
    suspend fun login(
        @Query("email") email : String,
        @Query("password") password : String
    ) : LoginResponse

    @POST("api/register")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ) : RegisterResponse

    @GET("api/products")
    suspend fun getAllProducts() : List<ProductTable>
}