package com.example.planta.view.login

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.planta.model.User
import com.example.planta.repository.UserRepository
import com.google.firebase.auth.FirebaseUser

class loginViewModel : ViewModel() {

    fun sign(email: String, password: String): LiveData<String> {
        var mLiveData = MutableLiveData<String>()

        UserRepository().sign(email, password)
            .observeForever {
                if (it != null) {
                    mLiveData.postValue(it)
                }

            }

        return mLiveData
    }

    fun getUserById(fb_id:String):MutableLiveData<List<User>>{
        return UserRepository().getUserById(fb_id)
    }





}
