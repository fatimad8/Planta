package com.example.planta.view.Home.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.planta.model.Cart
import com.example.planta.model.Product
import com.example.planta.repository.CartRepository

class CartViewModel:ViewModel() {

    fun addToCart(date: String,uId:String,price:String): LiveData<Boolean> {
        var mLiveData= MutableLiveData<Boolean>()
        CartRepository().addToCart(date, uId, price)
        .observeForever {
            if(it.uesrId.isNotEmpty()){
                mLiveData.postValue(true)
            }else{
                mLiveData.postValue(false)
            }

        }
        return  mLiveData
    }

    }
