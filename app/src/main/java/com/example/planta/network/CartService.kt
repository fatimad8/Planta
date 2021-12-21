package com.example.planta.network

import com.example.planta.model.*
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.GET


interface CartService {



    @POST("uesrs/{id}/order")
    fun createNewOrder(@Path("id")id:String, @Body order: Order): Call<Order>

    @PUT("uesrs/{id}/order/{id}")
    fun updateToatlPrice(@Path("id")uid:String,@Path("id")oid:String,@Body order: Order):Call<Order>

    @GET("uesrs/{id}/order")
    fun getOrderId(@Path("id")id:String,@Query("uesrId")oId: String):Call<List<Order>>

    @POST("uesrs/{id}/order/{id}/Product")
    fun addProductItem(@Path("id")uid:String,@Path("id")oId:String,@Body item:Item):Call<Item>

    @GET("uesrs/{id}/order/{id}/Product")
    fun getUserCart(@Path("id")uid:String,@Path("id")oId:String):Call<List<Item>>










}