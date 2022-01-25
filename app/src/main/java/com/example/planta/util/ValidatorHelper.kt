package com.example.planta.util

import android.text.TextUtils
import android.util.Patterns

class ValidatorHelper {

    companion object{
        fun passwordValidator(password: String): Boolean {
            var isValid: Boolean = false
            if (password.isEmpty() == true) {
                isValid = false
            } else {
                if (password.length >= 7)
                    isValid = true
            }

            return isValid
        }


        fun emailValidatore(email:String):Boolean{
            return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

    }



}