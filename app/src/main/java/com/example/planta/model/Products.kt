package com.example.planta.model

data class Products(
    val category: String,
    val description: String,
    val id: String,
    val inStock: Boolean,
    val name: String,
    val photo: String,
    val price: String,
    val quantity: Int
)