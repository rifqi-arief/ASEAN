package com.example.test.utils

sealed class ResourceData<out R> {
    data class Success<out T>(val data: T) : ResourceData<T>()
    data class Error(val errorMessage: String) : ResourceData<Nothing>()
    object Loading : ResourceData<Nothing>()
}