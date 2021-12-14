package com.example.planta.network

import com.example.planta.model.Cart
import com.example.planta.model.Order
import com.example.planta.model.Product
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.GET


interface CartService {

    @POST("order")
    fun addToCart(@Body order: Order): Call<Order>

    @GET("order")
    fun getUserCart(@Query("uesrId") uId: String): Call<List<Cart>>


    @GET("products")
    fun getCartItem(@Query("id") id:String): Call<List<Product>>

    @GET("cart")
    fun getProductsInCart(@Query("id") userId: String): Call<List<Product>>


}