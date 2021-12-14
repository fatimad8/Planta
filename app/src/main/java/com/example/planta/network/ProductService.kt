package com.example.planta.network

import com.example.planta.model.Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductService {




    @GET("products")
    fun getAllProducts(): Call<List<Product>>

    @GET("products/?category=indoor")
    fun getIndoor( ) :Call<List<Product>>

    @GET("products/?category=Outdoor")
    fun getOutndoor( ) :Call<List<Product>>

    @GET("products/?category=Accessories")
    fun getAccessories( ) :Call<List<Product>>

    @GET("products")
    fun search(@Query("name")key:String): Call<List<Product>>







}