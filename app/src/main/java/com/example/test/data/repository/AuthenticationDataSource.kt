package com.example.test.data.repository

import com.example.test.data.resource.api.AppApi
import com.example.test.domain.entity.response.LoginResponse
import com.example.test.domain.entity.response.RegisterRequest
import com.example.test.domain.entity.response.RegisterResponse
import com.example.test.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AuthenticationDataSource(private val api : AppApi) {

    suspend fun register(registerRequest: RegisterRequest): Flow<Resource<RegisterResponse>> {
        return flow {
            try {
                val response = api.register(registerRequest)
                if (response.message == "success"){
                    emit(Resource.Success(response))
                }else{
                    emit(Resource.Error(response.message.toString(), response))
                }
            } catch (e: Throwable) {
//                emit(Resource.Error(e.localizedMessage.toString(),null))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun login(email : String, password : String): Flow<Resource<LoginResponse>> {
        return flow {
            try {
                val response = api.login(email, password)
                if (response.message == "success"){
                    emit(Resource.Success(response))
                }else{
                    emit(Resource.Error(response.message.toString(), response))
                }
            } catch (e: Throwable) {
                emit(Resource.Error(e.localizedMessage.toString(),null))
            }
        }.flowOn(Dispatchers.IO)
    }

}