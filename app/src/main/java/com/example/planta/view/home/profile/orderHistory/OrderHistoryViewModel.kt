package com.example.planta.view.home.profile.orderHistory

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.planta.model.History
import com.example.planta.model.HistoryItem
import com.example.planta.model.Order
import com.example.planta.repository.CartRepository
import com.example.planta.repository.OrderHistoryRepository

class OrderHistoryViewModel:ViewModel() {


    fun createOrderHistory(uid:String,history:History):MutableLiveData<History>{
        var mLiveData = MutableLiveData<History>()
        OrderHistoryRepository().createOrderHistory(uid,history)
            .observeForever {
                if (it != null) {
                    mLiveData.postValue(it)
                } else {
                    mLiveData.postValue(History("", "", 0, ""))
                }

            }
        return mLiveData
    }

    fun addOrderToHistory(uid: String,hid:String,historyItem:HistoryItem):MutableLiveData<Boolean>{
        var mLiveData = MutableLiveData<Boolean>()
        OrderHistoryRepository().addOrderToHistory(uid,hid,historyItem)
            .observeForever {
                if (it.HistoryId.isNotEmpty()) {
                    mLiveData.postValue(true)
                } else {
                    mLiveData.postValue(false)
                }

            }
        return mLiveData

    }
}