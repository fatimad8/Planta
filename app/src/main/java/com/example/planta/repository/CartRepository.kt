package com.example.planta.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.planta.model.Cart
import com.example.planta.model.Order
import com.example.planta.model.Product
import com.example.planta.network.API
import com.example.planta.network.CartService
import com.example.planta.network.ProductService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class CartRepository {
    val cartService = API.getInstence().create(CartService::class.java)

    fun addToCart(id:String,date: String,uId:String,price:String,qun:Int): LiveData<Order> {
        var mLiveData = MutableLiveData<Order>()
        cartService.addToCart(id,Order("1",date,qun,price,uId))
            .enqueue(object : Callback<Order> {
                override fun onResponse(call: Call<Order>, response: Response<Order>) {
                    if (response.isSuccessful) {
                        mLiveData.postValue(response.body())
                    } else {
                        mLiveData.postValue(Order("", "", 0,"",""))
                    }
                }

                override fun onFailure(call: Call<Order>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })

        return mLiveData

    }

//    fun getUserCart(uId: String):LiveData<List<Cart>>{
//        var mLiveData = MutableLiveData<List<Cart>>()
//        cartService.getUserCart(uId)
//            .enqueue(object : Callback<List<Cart>> {
//                override fun onResponse(call: Call<List<Cart>>, response: Response<List<Cart>>) {
//                    if (response.isSuccessful) {
//                        mLiveData.postValue(response.body())
//                    } else {
//                        mLiveData.postValue(listOf(Cart("", "", "")))
//                    }
//                }
//
//                override fun onFailure(call: Call<List<Cart>>, t: Throwable) {
//                    TODO("Not yet implemented")
//                }
//
//            })
//
//        return mLiveData
//    }
//
//
//
//
//    fun getCartItem(Id:String):LiveData<List<Product>>{
//
//        var mLiveData = MutableLiveData<List<Product>>()
//        cartService.getCartItem(Id)
//            .enqueue(object : Callback<List<Product>> {
//                override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
//                    if (response.isSuccessful) {
//                        mLiveData.postValue(response.body())
//                    }
//                }
//                override fun onFailure(call: Call<List<Product>>, t: Throwable) {
//                    TODO("Not yet implemented")
//                }
//
//            })
//
//        return mLiveData
//    }

}