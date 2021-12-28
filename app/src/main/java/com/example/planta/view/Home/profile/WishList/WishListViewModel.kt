package com.example.planta.view.Home.profile.WishList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.planta.model.Liked
import com.example.planta.model.Order
import com.example.planta.model.WishList
import com.example.planta.repository.CartRepository
import com.example.planta.repository.WishlistRepository

class WishListViewModel:ViewModel() {

    fun WishList(id:String,wishList: WishList):LiveData<WishList>{
        var mLiveData=MutableLiveData<WishList>()
        WishlistRepository().wishList(id,wishList) .observeForever {
            if (it != null) {
                mLiveData.postValue(it)
            } else {
                mLiveData.postValue(WishList("", ""))
            }
        }
        return mLiveData
    }

    fun addLikedItem(uid:String,wid:String,liked: Liked):LiveData<Boolean>{
        var mLiveData = MutableLiveData<Boolean>()
        WishlistRepository().addLikedItem(uid, wid, liked)
            .observeForever {
                if (it.wishlistId.isNotEmpty()) {
                    mLiveData.postValue(true)
                } else {
                    mLiveData.postValue(false)
                }

            }
        return mLiveData
    }

    fun getUserWishlist(uid: String,wid: String):MutableLiveData<List<Liked>>{
        return WishlistRepository().getUserWishList(uid, wid)
    }

    fun getUserWishByUid(uid:String,userId:String):MutableLiveData<List<WishList>>{
        return WishlistRepository().getUserWishByUid(uid, userId)
    }

}