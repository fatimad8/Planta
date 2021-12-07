package com.example.planta.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.planta.network.UserService
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class UserRepository {

    fun sign(email: String, pass: String):Boolean{
        ///var user= MutableLiveData<FirebaseUser>()
        var auth = Firebase.auth
        var flag= false
        var user:FirebaseUser
        auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    user = auth.currentUser!!
                    flag=true
                    println("Login Success " + user?.uid)
                } else {
                   flag
                    println("login failed:"+task.exception)
                }
            }
         return flag
    }


}