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
    fun getOrderId(@Path("id")id:String,@Query("uesrId")userId: String):Call<List<Order>>

    @POST("uesrs/{id}/order/{uesrId}/Product")
    fun addProductItem(@Path("id")uid:String, @Path("uesrId")oId:String, @Body item:Item):Call<Item>

    @GET("uesrs/{id}/order/{oid}/Product")
    fun getUserCart(@Path("id")uid:String,@Path("oid")oId:String):Call<List<Item>>

    @DELETE("uesrs/{id}/order/{oid}/Product/{id1}")
    fun deleteItem(@Path("id")uid:String,@Path("oid")oId:String,@Path("id1")itemId:String):Call<Item>

    @PUT("uesrs/{id}/order/{oid}/Product/{id1}")
    fun updateCartQun(@Path("id")uid:String,@Path("oid")oId:String,@Path("id1")itemId:String,@Body item: Item):Call<Item>

    @GET("uesrs/{id}/order/{id}")
    fun getUserOrder(@Path("id")uid:String,@Path("id")oId:String):Call<Order>

    @DELETE("uesrs/{id}/order/{oid}")
    fun deleteUserCart(@Path("id")uid:String,@Path("oid")oid:String):Call<Item>

    @DELETE("uesrs/{id}/order/{oid}/Product")
    fun deleteCartItem(@Path("id")uid:String,@Path("oid")oid:String,@Query("orderId")orderId:String):Call<Item>












}