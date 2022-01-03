package com.example.planta.model

import java.io.Serializable

data class Address(
    val City: String,
    val Country: String,
    val State: String,
    val id: String,
    val postalCode: String,
    val uesrId: String
):Serializable