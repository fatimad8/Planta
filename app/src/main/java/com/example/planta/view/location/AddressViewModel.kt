package com.example.planta.view.location

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.planta.model.Address
import com.example.planta.repository.AddressRepository
import com.example.planta.repository.CartRepository

private const val TAG = "AddressViewModel"
class AddressViewModel:ViewModel() {
    val userAdresses = MutableLiveData<List<Address>>()

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

    fun getUserAddress(uid:String){
        AddressRepository().getUserAddress(uid)
            .observeForever {
                Log.d(TAG,"getUserAdress is called")
                if (it!=null) {
                    userAdresses.postValue(it)
                } else {
                    userAdresses.postValue(listOf(Address("","","","","","")))
                }

            }

    }
}