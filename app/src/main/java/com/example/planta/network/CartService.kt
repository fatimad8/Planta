package com.example.planta.network

import com.example.planta.model.Cart
import com.example.planta.model.Product
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.GET


interface CartService {

    @POST("cart")
    fun addToCart(@Body cart: Cart): Call<Cart>

    @GET("cart")
    fun getUserCart(@Query("uId") uId: String): Call<List<Cart>>


    @GET("products")
    fun getCartItem(@Query("id") id:String): Call<List<Product>>

    @GET("cart")
    fun getProductsInCart(@Query("id") userId: String): Call<List<Product>>


}