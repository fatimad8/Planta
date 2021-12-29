package com.example.planta.network

import com.example.planta.model.History
import com.example.planta.model.HistoryItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface OrderHistoryService {

    @POST("uesrs/{id}/History")
    fun createOrderHistory(@Path("id")id:String,@Body history:History):Call<History>

    @POST("uesrs/{id}/History/{hid}/HistoryItem")
    fun addOrderToHistory(@Path ("id")id:String,@Path("hid")hid:String,@Body historyItem:HistoryItem):Call<HistoryItem>
}