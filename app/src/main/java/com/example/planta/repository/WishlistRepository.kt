package com.example.planta.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.planta.model.Item
import com.example.planta.model.Liked
import com.example.planta.model.WishList
import com.example.planta.network.API
import com.example.planta.network.CartService
import com.example.planta.network.WishListService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WishlistRepository {
    val wishService = API.getInstence().create(WishListService::class.java)

    fun wishList(id: String, wishList: WishList): LiveData<WishList> {
        var mLiveData = MutableLiveData<WishList>()
        wishService.wishList(id, wishList).enqueue(object : Callback<WishList> {
            override fun onResponse(call: Call<WishList>, response: Response<WishList>) {
                if (response.isSuccessful) {
                    mLiveData.postValue(response.body())
                } else {
                    mLiveData.postValue(WishList("", ""))
                }
            }

            override fun onFailure(call: Call<WishList>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
        return mLiveData
    }

    fun addLikedItem(uid: String, wid: String, liked: Liked): LiveData<Liked> {
        var mLiveData = MutableLiveData<Liked>()
        wishService.addLikedItem(uid, wid, liked).enqueue(object : Callback<Liked> {
            override fun onResponse(call: Call<Liked>, response: Response<Liked>) {
                if (response.isSuccessful) {
                    mLiveData.postValue(response.body())
                } else {
                    mLiveData.postValue(Liked("", "", "", "", "", "", 0, ""))
                }

            }

            override fun onFailure(call: Call<Liked>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
        return mLiveData
    }

    fun getUserWishList(uid: String, wid: String):MutableLiveData<List<Liked>> {
        var mLiveData = MutableLiveData<List<Liked>>()
        wishService.getUserWishlist(uid, wid)
            .enqueue(object : Callback<List<Liked>> {
                override fun onResponse(
                    call: Call<List<Liked>>,
                    response: Response<List<Liked>>
                ) {
                    mLiveData.postValue(response.body())
                }

                override fun onFailure(call: Call<List<Liked>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        return mLiveData
    }


    fun getUserWishByUid(uid: String, wid: String): MutableLiveData<List<WishList>> {
        var mLiveData = MutableLiveData<List<WishList>>()
        wishService.getUserWishByUid(uid, wid).enqueue(object : Callback<List<WishList>> {
            override fun onResponse(
                call: Call<List<WishList>>,
                response: Response<List<WishList>>
            ) {
                mLiveData.postValue(response.body())
            }

            override fun onFailure(call: Call<List<WishList>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
        return mLiveData
    }


}