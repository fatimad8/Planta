package com.example.planta.model

data class Liked(
    val category: String,
    val description: String,
    val id: String,
    val name: String,
    val photo: String,
    val price: Int,
    val quantity: Int,
    val wishlistId: String
)