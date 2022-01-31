package com.example.planta.view.home.cart

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.planta.model.*
import com.example.planta.repository.CartRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "CartViewModel"
class CartViewModel:ViewModel() {

    val updatedQuantity = MutableLiveData<Boolean>()
    val userCart = MutableLiveData<List<Item>>()

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
                var mutableLiveData = MutableLiveData<List<Item>>()
                CartRepository().getUserCart(uId,oId)
            .enqueue(object : Callback<List<Item>> {
                override fun onResponse(
                    call: Call<List<Item>>,
                    response: Response<List<Item>>
                ) {
                    mutableLiveData.postValue(response.body())
                    userCart.postValue(response.body())
                    Log.d(TAG,"GetUserCart is called")
                    Log.d(TAG,"userCart: $userCart")
                }

                override fun onFailure(call: Call<List<Item>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
            return mutableLiveData
    }

    fun deleteItem(uId: String, oId: String,itemId:String):MutableLiveData<Boolean>{
        var mLiveData = MutableLiveData<Boolean>()
        return CartRepository().deleteItem(uId, oId, itemId)
    }


    fun deleteUserCart(uid:String,oId: String):MutableLiveData<Boolean>{
        return CartRepository().deleteUserCart(uid, oId)

    }


    fun updateCartQun(uId: String, oId: String,itemId:String,item: Item){
        var mLiveData = MutableLiveData<Boolean>()
        CartRepository().updateCartQun(uId, oId, itemId,item)
            .observeForever {
                if (it!=null) {
                    Log.d(TAG,"Inside the view model if")
                    updatedQuantity.postValue(true)
                    Log.d(TAG,"${updatedQuantity.value}")
                } else {
                    Log.d(TAG,"Inside the view model else ")
                    updatedQuantity.postValue(false)
                }
            }

    }

    fun updateCartQun1(uId: String, oId: String,itemId:String,item: Item){
        CartRepository().updateCartQun1(uId,oId, itemId, item).enqueue(object : Callback<Item> {
            override fun onResponse(
                call: Call<Item>,
                response: Response<Item>
            ) {
                if(response.isSuccessful){
                    Log.d(TAG,"Update is successfully")
                    getUserCart(uId, oId)
                    Log.d(TAG,"user cart in view model ")

                }else{


                }

            }

            override fun onFailure(call: Call<Item>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }


    fun getUserOrder(uId: String, oId: String): LiveData<Order> {
        return CartRepository().getUserOrder(uId, oId)

    }


}
