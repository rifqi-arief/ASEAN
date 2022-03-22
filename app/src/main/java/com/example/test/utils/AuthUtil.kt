package com.example.test.utils

class AuthUtil {
    val users = listOf("Peter", "John", "Smith")

    fun signUp(
        userName: String,
        password: String,
        repeatPassword: String
    ): Boolean{
        return true
    }

    fun finalPrice(basePrice: Double): Double {
        return basePrice * 1.5
    }
}