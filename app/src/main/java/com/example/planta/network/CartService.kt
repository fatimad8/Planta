package com.example.planta.network

import com.example.planta.model.Cart
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface CartService {

    @POST("cart")
    fun addToCart(@Body cart:Cart): Call<Cart>


}