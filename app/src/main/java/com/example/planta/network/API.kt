package com.example.planta.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API {

    companion object{
        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://61b85f9964e4a10017d18f12.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        fun getInstence():Retrofit{
            return retrofit
        }
    }

}