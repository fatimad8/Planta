package com.example.planta.network

import com.example.planta.model.*
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.GET


interface CartService {



    @POST("uesrs/{id}/order")
    fun createNewOrder(@Path("id")id:String, @Body order: Order): Call<Order>

    @PUT("uesrs/{id}/order/{id}")
    fun updateToatlPrice(@Path("id")uid:String,@Path("id")oid:String,@Body price:Int):Call<Order>

    @GET("uesrs/{id}/order")
    fun getOrderId(@Path("id")id:String,@Query("uesrId")oId: String):Call<List<Order>>

    @POST("uesrs/{id}/order/{id}/Product")
    fun addProductItem(@Path("id")uid:String,@Path("id")oId:String,@Body item:Item):Call<Item>







    @GET("order")
    fun getUserCart(@Query("uesrId") uId: String): Call<List<Cart>>

    @GET("products")
    fun getCartItem(@Query("id") id:String): Call<List<Product>>

    @GET("cart")
    fun getProductsInCart(@Query("id") userId: String): Call<List<Product>>


}