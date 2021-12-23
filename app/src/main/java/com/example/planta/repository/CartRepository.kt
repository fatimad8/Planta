package com.example.planta.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.planta.model.Item
import com.example.planta.model.Order
import com.example.planta.model.Product
import com.example.planta.model.User
import com.example.planta.network.API
import com.example.planta.network.CartService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartRepository {
    val cartService = API.getInstence().create(CartService::class.java)

    fun createNewOrder(
        id: String,
        date: String,
        uId: String,
        price: String,
        qun: Int
    ): LiveData<Order> {
        var mLiveData = MutableLiveData<Order>()
        cartService.createNewOrder(id, Order("1", date, qun, price, uId))
            .enqueue(object : Callback<Order> {
                override fun onResponse(call: Call<Order>, response: Response<Order>) {
                    if (response.isSuccessful) {
                        mLiveData.postValue(response.body())
                    } else {
                        mLiveData.postValue(Order("", "", 0, "", ""))
                    }
                }

                override fun onFailure(call: Call<Order>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })

        return mLiveData

    }



    fun addProductItem(uId: String,oId: String,item: Item):LiveData<Item>{
        var mLiveData = MutableLiveData<Item>()
        cartService.addProductItem(uId,oId,item).enqueue(object : Callback<Item> {
            override fun onResponse(call: Call<Item>, response: Response<Item>) {
                if (response.isSuccessful) {
                    mLiveData.postValue(response.body())
                } else {
                    //mLiveData.postValue(Item("", "", "", "", "","","",0,0))
                }
            }

            override fun onFailure(call: Call<Item>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

        return mLiveData


    }


    fun updateTotalPrice(uId: String, oId: String, order: Order): LiveData<Order> {
        var mLiveData = MutableLiveData<Order>()
        cartService.updateToatlPrice(uId, oId, order).enqueue(object : Callback<Order> {
            override fun onResponse(call: Call<Order>, response: Response<Order>) {
                if (response.isSuccessful) {
                    mLiveData.postValue(response.body())
                } else {
                    mLiveData.postValue(Order("", "", 0, "", ""))
                }
            }

            override fun onFailure(call: Call<Order>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

        return mLiveData

    }


    fun getOrderId(userId: String): MutableLiveData<List<Order>> {
        var mLiveData = MutableLiveData<List<Order>>()

        cartService.getOrderId(userId,userId)
            .enqueue(object : Callback<List<Order>> {
                override fun onResponse(
                    call: Call<List<Order>>,
                    response: Response<List<Order>>
                ) {
                    mLiveData.postValue(response.body())
                }

                override fun onFailure(call: Call<List<Order>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        return mLiveData
    }

    fun getUserCart(uId: String, oId: String): LiveData<List<Item>>{
        var mutableLiveData = MutableLiveData<List<Item>>()

        cartService.getUserCart(uId,oId)
            .enqueue(object : Callback<List<Item>> {
                override fun onResponse(
                    call: Call<List<Item>>,
                    response: Response<List<Item>>
                ) {
                    mutableLiveData.postValue(response.body())
                }

                override fun onFailure(call: Call<List<Item>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        return mutableLiveData

    }

    fun deleteItem(uId: String, oId: String,itemId:String):MutableLiveData<Boolean>{
        var mutableLiveData = MutableLiveData<Boolean>()
        cartService.deleteItem(uId,oId, itemId).enqueue(object : Callback<Item> {
            override fun onResponse(
                call: Call<Item>,
                response: Response<Item>
            ) {
                if(response.isSuccessful){
                    mutableLiveData.postValue(true)
                }else{
                    mutableLiveData.postValue(false)

                }

            }

            override fun onFailure(call: Call<Item>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
        return mutableLiveData

    }

    fun updateCartQun(uId: String, oId: String,itemId:String,item:Item):LiveData<Item>{
        var mLiveData = MutableLiveData<Item>()
         cartService.updateCartQun(uId,oId, itemId, item).enqueue(object : Callback<Item> {
            override fun onResponse(
                call: Call<Item>,
                response: Response<Item>
            ) {
                if(response.isSuccessful){
                    mLiveData.postValue(response.body())
                }else{
                    mLiveData.postValue(Item("","","","","","","","",0))

                }

            }

            override fun onFailure(call: Call<Item>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
        return mLiveData

    }




}