package com.example.planta.model

import java.io.Serializable

data class Product(
    val category: String,
    val description: String,
    val id: String,
    val inStock: Boolean,
    val name: String,
    val photo: String,
    val price: String
): Serializable