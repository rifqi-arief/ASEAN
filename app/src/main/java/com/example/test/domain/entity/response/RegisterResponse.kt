package com.example.test.domain.entity.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RegisterResponse (
    @field:SerializedName("message") val message: String,
    @field:SerializedName("data") val data: User,
) : Parcelable

@Parcelize
data class User (
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("email") val email: String,
) : Parcelable