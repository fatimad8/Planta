package com.example.planta.network

import com.example.planta.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("uesrs")
    fun addUser(@Body user: User): Call<User>
}