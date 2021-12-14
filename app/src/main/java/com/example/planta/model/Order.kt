package com.example.planta.model

import java.io.Serializable

data class Order(
    val id: String,
    val order_date: String,
    val total_price: String,
    val uesrId: String
):Serializable