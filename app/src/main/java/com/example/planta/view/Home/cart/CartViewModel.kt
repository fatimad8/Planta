package com.example.planta.view.Home.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.planta.repository.CartRepository

class CartViewModel:ViewModel() {

    fun addToCart(pId:String,uId:String): LiveData<Boolean> {
        var mLiveData= MutableLiveData<Boolean>()
        CartRepository().addToCart(pId, uId)
        .observeForever {
            if(it.pId.isNotEmpty()&&it.uId.isNotEmpty()){
                mLiveData.postValue(true)
            }else{
                mLiveData.postValue(false)
            }

        }
        return  mLiveData
    }
}