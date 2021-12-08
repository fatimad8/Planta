package com.example.planta.repository

import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.planta.network.UserService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
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


    fun register(name:String,email: String, pass: String):Boolean{
        var auth= Firebase.auth
        var flag=false

        auth.createUserWithEmailAndPassword(email,pass)
            .addOnCompleteListener { task->

                if(task.isSuccessful){

                    println("User has been registered successfully with UID "+ auth.currentUser?.uid)
                    flag=true

                    val u = hashMapOf(
                        "email" to auth.currentUser?.email,
                        "firstname" to name.toString()

                    )

                    var db= Firebase.firestore

                    db.collection("users").document(auth.currentUser?.uid.toString())
                        .set(u)
                }else{
                    flag=false

                    println("Error")
                }
            }.addOnFailureListener {
                println(it.message)
            }
        return flag
    }


    fun resetPassword(email: String):Boolean{
        var mAuth: FirebaseAuth? = null
        var flag=false
        mAuth = FirebaseAuth.getInstance()
        mAuth!!.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    flag=true
                  } else {
                    flag=false
                }
            }
        return flag
    }


}