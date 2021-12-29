package com.example.planta.repository

import androidx.lifecycle.MutableLiveData
import com.example.planta.model.History
import com.example.planta.model.HistoryItem
import com.example.planta.network.API
import com.example.planta.network.CartService
import com.example.planta.network.OrderHistoryService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderHistoryRepository {

    val historyService = API.getInstence().create(OrderHistoryService::class.java)

    fun createOrderHistory(uid: String, history: History): MutableLiveData<History> {
        var mLiveData = MutableLiveData<History>()
        historyService.createOrderHistory(uid, history).enqueue(object : Callback<History> {
            override fun onResponse(call: Call<History>, response: Response<History>) {
                if (response.isSuccessful) {
                    mLiveData.postValue(response.body())
                } else {
                    mLiveData.postValue(History("", "", 0, ""))
                }
            }

            override fun onFailure(call: Call<History>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
        return mLiveData
    }

    fun addOrderToHistory(
        uid: String,
        hid: String,
        historyItem: HistoryItem
    ): MutableLiveData<HistoryItem> {
        var mLiveData = MutableLiveData<HistoryItem>()
        historyService.addOrderToHistory(uid, hid, historyItem)
            .enqueue(object : Callback<HistoryItem> {
                override fun onResponse(call: Call<HistoryItem>, response: Response<HistoryItem>) {
                    if (response.isSuccessful) {
                        mLiveData.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<HistoryItem>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        return mLiveData
    }


    fun getOrderHistory(uid: String): MutableLiveData<List<History>> {
        var mLiveData = MutableLiveData<List<History>>()
        historyService.getOrderHistory(uid).enqueue(object : Callback<List<History>> {
            override fun onResponse(call: Call<List<History>>, response: Response<List<History>>) {
                mLiveData.postValue(response.body())
            }

            override fun onFailure(call: Call<List<History>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
        return mLiveData
    }


    fun getOrderItem(uid: String, hid: String): MutableLiveData<List<HistoryItem>> {
        var mLiveData = MutableLiveData<List<HistoryItem>>()
        historyService.getOrderItem(uid, hid).enqueue(object : Callback<List<HistoryItem>> {
            override fun onResponse(
                call: Call<List<HistoryItem>>,
                response: Response<List<HistoryItem>>
            ) {
                mLiveData.postValue(response.body())
            }

            override fun onFailure(call: Call<List<HistoryItem>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
        return mLiveData
    }

}