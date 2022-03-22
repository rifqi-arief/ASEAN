package com.example.test

import com.example.test.utils.Validator.isEmail
import org.junit.Test
import org.junit.Assert.*

class AppUnitTest {

    fun `login form kosong`() {

    }

    fun `login username kosong`() {

    }

    fun `login password kosong`() {

    }

    @Test
    fun `cek email format com`() {
        val mail = "mail@mail.com"
        assertTrue("Format email ${mail} salah",mail.isEmail())
    }

    @Test
    fun `cek email format co id`() {
        val mail = "mail@mail.co.id"
        assertTrue("Format email ${mail} salah",mail.isEmail())
    }

    @Test
    fun `cek email format salah`() {
        val mail = "mail@mail"
        assertTrue("Format email terbaca benar",!mail.isEmail())
    }

}