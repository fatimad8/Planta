package com.example.planta.network

import com.example.planta.model.Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductService {

    @GET("products/{category}")
    fun getIndoor(@Path("category")category:String): Call<List<Product>>

}