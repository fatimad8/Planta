package com.example.planta.repository

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.planta.model.User
import com.example.planta.network.API
import com.example.planta.network.UserService
import com.example.planta.util.SharedPreferencesHelper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {

    var userService = API.getInstence().create(UserService::class.java)
    var auth = Firebase.auth

    fun sign(email: String, pass: String): LiveData<String> {

        var mLiveData = MutableLiveData<User>()
        var flag = MutableLiveData<String>()
        if (email.isNotEmpty() && pass.isNotEmpty()) {
            auth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        auth.currentUser!!.uid
                        flag.postValue(auth.currentUser!!.uid)
                    }
                }.addOnFailureListener {
                    println("exception:$it")
                }
        }

        return flag
    }


    fun register(name: String, email: String, pass: String): MutableLiveData<Boolean> {
        var auth = Firebase.auth
        var db = Firebase.firestore
        var flag = MutableLiveData<Boolean>()
        if (email.isNotEmpty() && pass.isNotEmpty() && name.isNotEmpty()) {
            auth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener { task ->

                    if (task.isSuccessful) {

                        flag.postValue(true)

                        val u = hashMapOf(
                            "email" to auth.currentUser?.email.toString(),
                            "fullname" to name
                        )

                        //var uid=auth.currentUser?.uid
                        val uid = FirebaseAuth.getInstance().currentUser!!.uid
                        db.collection("users").document(uid)
                            .set(u)
                    } else {
                        flag.postValue(false)
                    }
                }
                .addOnFailureListener {
                    println(it.message)
                }
        }

        return flag
    }

    fun addUser(user: User): LiveData<User> {
        var mLiveData = MutableLiveData<User>()
        userService.addUser(user)
            .enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        mLiveData.postValue(response.body())
                    } else {
                        mLiveData.postValue(User("", "", ""))
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })

        return mLiveData

    }


    fun resetPassword(email: String): MutableLiveData<Boolean> {
        var mAuth = Firebase.auth
        var flag = MutableLiveData<Boolean>()
        if (email.isNotEmpty()) {
            mAuth = FirebaseAuth.getInstance()
            mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    flag.postValue(true)
                }
        }
        return flag
    }


    fun checkLogin(user: FirebaseUser?): MutableLiveData<Boolean> {
        var mLiveData = MutableLiveData<Boolean>()

        if (user == null) {
            mLiveData.postValue(false)
        } else {
            mLiveData.postValue(true)
        }
        return mLiveData
    }


    fun getUserById(fb_id: String): MutableLiveData<List<User>> {
        var mLiveData = MutableLiveData<List<User>>()

        userService.getUserById(fb_id)
            .enqueue(object : Callback<List<User>> {
                override fun onResponse(
                    call: Call<List<User>>,
                    response: Response<List<User>>) {
                    mLiveData.postValue(response.body())
                }


                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    TODO("Not yet implemented")
                }


            })
        return mLiveData
    }
}