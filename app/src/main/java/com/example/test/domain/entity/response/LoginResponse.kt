package com.example.test.domain.entity.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginResponse (
    @field:SerializedName("message") val message: String,
) : Parcelable