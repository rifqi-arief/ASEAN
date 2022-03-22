package com.example.test.utils

object Validator {

    fun String.isEmail(): Boolean {
        val emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$".toRegex()
        return this.matches(emailRegex)
    }

}