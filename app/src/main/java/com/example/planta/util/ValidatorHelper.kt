package com.example.planta.util

class ValidatorHelper {


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
}