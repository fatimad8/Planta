package com.example.planta.model

import java.io.Serializable

data class History(
    val createdAt: String,
    val id: String,
    val total_price: Int,
    val uesrId: String
):Serializable