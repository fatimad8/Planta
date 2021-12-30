package com.example.planta.repository

import androidx.lifecycle.MutableLiveData
import com.example.planta.model.Address
import com.example.planta.network.API
import com.example.planta.network.AddressService
import com.example.planta.network.CartService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddressRepository {

    val addressService = API.getInstence().create(AddressService::class.java)

    fun saveUserAddress(uid:String,address:Address):MutableLiveData<Address>{
        var mLiveData=MutableLiveData<Address>()
        addressService.saveUserAddress(uid,address).enqueue(object : Callback<Address> {
            override fun onResponse(call: Call<Address>, response: Response<Address>) {
                if(response.isSuccessful)
                    mLiveData.postValue(response.body())
             }

            override fun onFailure(call: Call<Address>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
        return mLiveData
    }

}