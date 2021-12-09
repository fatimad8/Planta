package com.example.planta.view.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.planta.repository.UserRepository

class RegisterViewModel : ViewModel() {
    fun register(name: String, email: String, pass: String):LiveData<Boolean> {
        var mLiveData = MutableLiveData<Boolean>()
        UserRepository().register(name, email, pass).observeForever {
            if (it){
                mLiveData.postValue(true)
            }
            else {
                mLiveData.postValue(false)
            }
        }
        return mLiveData
    }


}