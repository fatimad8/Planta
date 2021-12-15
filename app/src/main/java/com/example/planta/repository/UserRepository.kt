package com.example.planta.repository

import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.planta.model.User
import com.example.planta.network.API
import com.example.planta.network.UserService
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


    fun sign(email: String, pass: String):LiveData<Boolean>{
        var auth = Firebase.auth
        var flag =MutableLiveData<Boolean>()
         if(email.isNotEmpty()&&pass.isNotEmpty()){
            auth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        auth.currentUser!!
                        flag.postValue(true)
                        println("it is true")
                     }else{
                         flag.postValue(false)
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

    fun addUser(fId:String,id:String,name:String):LiveData<User>{
        var mLiveData = MutableLiveData<User>()
        userService.addUser(User(fId,id,name))
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


    fun resetPassword(email: String):MutableLiveData<Boolean> {
        var mAuth=Firebase.auth
        var flag = MutableLiveData<Boolean>()
        if(email.isNotEmpty()){
            mAuth = FirebaseAuth.getInstance()
            mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    flag.postValue(true)
                 }
        }
        return flag
    }


}