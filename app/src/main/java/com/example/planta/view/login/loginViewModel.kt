package com.example.planta.view.login

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.planta.repository.UserRepository
import com.google.firebase.auth.FirebaseUser

class loginViewModel : ViewModel() {

    fun sign(email: String, password: String): LiveData<Boolean> {
        var mLiveData = MutableLiveData<Boolean>()

        UserRepository().sign(email, password)
            .observeForever {
                if(it){
                    mLiveData.postValue(true)
                }else{
                    mLiveData.postValue(false)
                }

            }

        return mLiveData
    }





}
