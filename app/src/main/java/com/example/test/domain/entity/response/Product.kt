package com.example.test.domain.entity.response

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

//@Parcelize
//data class Product (
//    @field:SerializedName("id") val id: Int,
//    @field:SerializedName("name") val name: String,
//    @field:SerializedName("description") val description: String,
//    @field:SerializedName("price") val price: Int,
//    @field:SerializedName("date") val date: String,
//    @field:SerializedName("image") val image: String,
//) : Parcelable

@Entity(tableName = "products")
data class ProductTable (
    @PrimaryKey @field:SerializedName("id") val id: Int,
    @NonNull @field:SerializedName("name") val name: String,
    @NonNull @field:SerializedName("description") val description: String,
    @NonNull @field:SerializedName("price") val price: Int,
    @NonNull @field:SerializedName("date") val date: String,
    @NonNull @field:SerializedName("image") val image: String,
)
