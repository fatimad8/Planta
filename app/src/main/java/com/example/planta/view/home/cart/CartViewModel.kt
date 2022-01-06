package com.example.planta.view.home.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.planta.model.*
import com.example.planta.repository.CartRepository

class CartViewModel:ViewModel() {

    fun createNewOrder(
        id: String,
       order:Order
    ): LiveData<Order> {
        var mLiveData = MutableLiveData<Order>()
        CartRepository().createNewOrder(id,order)
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


    fun getOrderId(userId: String,uid:String): MutableLiveData<List<Order>> {
        return CartRepository().getOrderId(userId,uid)
    }


    fun getUserCart(uId: String, oId: String): LiveData<List<Item>> {
        return CartRepository().getUserCart(uId, oId)

    }

    fun deleteItem(uId: String, oId: String,itemId:String):MutableLiveData<Boolean>{
        var mLiveData = MutableLiveData<Boolean>()
        return CartRepository().deleteItem(uId, oId, itemId)
    }


    fun deleteUserCart(uid:String,oId: String):MutableLiveData<Boolean>{
        return CartRepository().deleteUserCart(uid, oId)

    }


    fun updateCartQun(uId: String, oId: String,itemId:String,item: Item):LiveData<Boolean>{
        var mLiveData = MutableLiveData<Boolean>()
        CartRepository().updateCartQun(uId, oId, itemId,item)
            .observeForever {
                if (it!=null) {
                    mLiveData.postValue(true)
                } else {
                    mLiveData.postValue(false)
                }

            }
        return mLiveData
    }


    fun getUserOrder(uId: String, oId: String): LiveData<Order> {
        return CartRepository().getUserOrder(uId, oId)

    }


}
