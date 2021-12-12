package com.example.planta.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.planta.model.Cart
import com.example.planta.network.API
import com.example.planta.network.CartService
import com.example.planta.network.ProductService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartRepository {
    val cartService = API.getInstence().create(CartService::class.java)

    fun addToCart(pId:String,uId:String): LiveData<Cart> {
        var mLiveData = MutableLiveData<Cart>()
        cartService.addToCart(Cart("1",pId, uId))
            .enqueue(object : Callback<Cart> {
                override fun onResponse(call: Call<Cart>, response: Response<Cart>) {
                    if (response.isSuccessful) {
                        mLiveData.postValue(response.body())
                    } else {
                        mLiveData.postValue(Cart("", "", ""))
                    }
                }

                override fun onFailure(call: Call<Cart>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })

        return mLiveData

    }

}