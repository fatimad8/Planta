package com.example.planta.network

import com.example.planta.model.Address
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface AddressService {

    @POST("uesrs/{id}/Address")
    fun saveUserAddress(@Path("id")id:String,@Body address: Address): Call<Address>
}