package com.example.planta.view.forgetPassword

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.planta.repository.UserRepository

class ForgetViewModel:ViewModel() {

    fun resetPassword(email: String): LiveData<Boolean> {
        var mLiveData = MutableLiveData<Boolean>()

        UserRepository().resetPassword(email).observeForever {
            if(it)
                mLiveData.postValue(true)
            else {
                mLiveData.postValue(false)
            }
        }
        return mLiveData
    }
}