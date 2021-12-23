package com.example.planta.view.Home.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.planta.model.*
import com.example.planta.repository.CartRepository
import com.example.planta.repository.UserRepository

class CartViewModel:ViewModel() {

    fun createNewOrder(
        id: String,
        date: String,
        uId: String,
        price: String,
        qun: Int
    ): LiveData<Order> {
        var mLiveData = MutableLiveData<Order>()
        CartRepository().createNewOrder(id, date, uId, price, qun)
            .observeForever {
                if (it != null) {
                    mLiveData.postValue(it)
                } else {
                    mLiveData.postValue(Order("", "", 0, "", ""))
                }

            }
        return mLiveData
    }


    fun addProductItem(uId: String, oId: String, item: Item): LiveData<Boolean> {
        var mLiveData = MutableLiveData<Boolean>()
        CartRepository().addProductItem(uId, oId, item)
            .observeForever {
                if (it.orderId.isNotEmpty()) {
                    mLiveData.postValue(true)
                } else {
                    mLiveData.postValue(false)
                }

            }
        return mLiveData

    }

    fun updateTotalPrice(uId: String, oId: String, order: Order): LiveData<Boolean> {
        var mLiveData = MutableLiveData<Boolean>()
        CartRepository().updateTotalPrice(uId, oId, order)
            .observeForever {
                if (it.uesrId.isNotEmpty()) {
                    mLiveData.postValue(true)
                } else {
                    mLiveData.postValue(false)
                }

            }
        return mLiveData
    }


    fun getOrderId(userId: String): MutableLiveData<List<Order>> {
        return CartRepository().getOrderId(userId)
    }


    fun getUserCart(uId: String, oId: String): LiveData<List<Item>> {
        return CartRepository().getUserCart(uId, oId)

    }

    fun deleteItem(uId: String, oId: String,itemId:String):MutableLiveData<Boolean>{
        var mLiveData = MutableLiveData<Boolean>()
        return CartRepository().deleteItem(uId, oId, itemId)
    }

}
