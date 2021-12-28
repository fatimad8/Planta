package com.example.planta.view.home.profile.userProfile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.planta.repository.UserRepository
import com.google.firebase.auth.FirebaseUser

class ProfileViewModel : ViewModel() {

    fun checkLogin(user: FirebaseUser?): MutableLiveData<Boolean> {
        var mLiveData = MutableLiveData<Boolean>()
        UserRepository().checkLogin(user).observeForever {
            if (it) {
                mLiveData.postValue(true)
            } else {
                mLiveData.postValue(false)

            }
        }
        return mLiveData
    }
}