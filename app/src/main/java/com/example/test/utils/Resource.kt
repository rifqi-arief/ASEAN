package com.example.test.utils

sealed class Resource<out T>(
    val data: T? = null,
    val error: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(error: String, data: T? = null) : Resource<T>(data, error)
}

//sealed class Resource<out T>{
//    class Success<T>(val data: T) : Resource<T>()
//    class Error(val error: String) : Resource<Nothing>()
//    object Loading : Resource<Nothing>()
//
//    override fun toString(): String {
//        return when(this) {
//            is Success<*> -> "Success[data=$data]"
//            is Error -> "Error[exception=$error]"
//            Loading -> "Loading"
//        }
//    }
//}