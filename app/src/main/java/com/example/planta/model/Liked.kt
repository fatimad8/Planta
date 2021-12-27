package com.example.planta.model

import java.io.Serializable

data class Liked(
    val category: String,
    val description: String,
    val id: String,
    val name: String,
    val photo: String,
    val price: String,
    val quantity: Int,
    val wishlistId: String
):Serializable