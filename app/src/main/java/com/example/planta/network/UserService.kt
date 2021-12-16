package com.example.planta.network

import com.example.planta.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserService {
    @POST("uesrs")
    fun addUser(@Body user: User): Call<User>

    @GET("uesrs")
    fun getUserId(@Query("fb_id")fb_id:String):Call<List<User>>
}