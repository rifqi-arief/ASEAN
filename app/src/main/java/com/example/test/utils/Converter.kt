package com.example.test.utils

import java.text.NumberFormat
import java.util.*

object Converter {

    fun Any.toRupiah(): String {
        val localId = Locale("in", "ID")
        val formatter = NumberFormat.getCurrencyInstance(localId)
        val strFormat = formatter.format(this).replace(",00", "")
        return strFormat
    }

    fun String.toPhoneNumber() : String{
        return this.replaceFirstChar { "62" }
    }

}