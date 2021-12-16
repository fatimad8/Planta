package com.example.planta.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor




class API {

    companion object{

        fun getClient(): OkHttpClient {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
            return client

        }

        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://61b85f9964e4a10017d18f12.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(getClient())
            .build()

        fun getInstence():Retrofit{
            return retrofit
        }
    }

}