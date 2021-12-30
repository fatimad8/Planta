package com.example.planta.view.location

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.planta.model.Address
import com.example.planta.repository.AddressRepository
import com.example.planta.repository.CartRepository

class AddressViewModel:ViewModel() {

    fun saveUserAddress(uid:String,address:Address):MutableLiveData<Boolean>{
        var mLiveData = MutableLiveData<Boolean>()
         AddressRepository().saveUserAddress(uid,address)
            .observeForever {
                if (it!=null) {
                    mLiveData.postValue(true)
                } else {
                    mLiveData.postValue(false)
                }

            }
        return mLiveData
    }

    fun getUserAddress(uid:String):MutableLiveData<List<Address>>{
        var mLiveData = MutableLiveData<List<Address>>()
        AddressRepository().getUserAddress(uid)
            .observeForever {
                if (it!=null) {
                    mLiveData.postValue(it)
                } else {
                    mLiveData.postValue(listOf(Address("","","","","","")))
                }

            }
        return mLiveData
    }
}