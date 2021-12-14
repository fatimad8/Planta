package com.example.planta.view.Home.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.planta.model.Cart
import com.example.planta.model.Product
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


    fun getUserCart(uId:String):LiveData<List<Cart>>{
        var mLiveData= MutableLiveData<List<Cart>>()
        CartRepository().getUserCart(uId)
            .observeForever {
               mLiveData.postValue(it)
            }
        return  mLiveData

    }




    fun getCartItem(Id:String):LiveData<List<Product>>{
        var mLiveData= MutableLiveData<List<Product>>()
        CartRepository().getCartItem(Id)
            .observeForever {
                mLiveData.postValue(it)

            }
        return  mLiveData

    }
}