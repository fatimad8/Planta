package com.example.planta.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API {

    companion object{
        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://61b0a8873c954f001722a555.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        fun getInstence():Retrofit{
            return retrofit
        }
    }

}