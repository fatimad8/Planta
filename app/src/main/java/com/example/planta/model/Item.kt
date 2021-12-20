package com.example.planta.model

import java.io.Serializable

data class Item(
    val orderId: String,

    val category: String,
    val createdAt: String,
    val description: String,
    val id: String,
    val name: String,
    val photo: String,
    val price: String,
    val quantity: Int
) : Serializable
