package com.example.planta

import androidx.core.util.PatternsCompat
import com.example.planta.util.ValidatorHelper
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Assert
import org.junit.Test

class EmailValidateTest {


    fun emailVerification(email:String):Boolean{
        var isValid:Boolean
        if(email.isEmpty()==true){
            isValid=false
        }
        val matches=PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
        if(!matches){
            isValid=false
        }else{
            isValid=true
        }
        return isValid

    }



    @Test
    fun emailValidator_CorrectEmailSimple() {
        assertTrue(emailVerification("fatm@gmail.com"))
        assertTrue(emailVerification("fatm@google.com"))
        assertFalse(emailVerification("fatm@google"))
        assertFalse(emailVerification("fatm.com"))
        assertFalse(emailVerification("fatm"))
    }

    @Test
    fun passwordValidator_CorrectPasswordSimple(){
        assertTrue(ValidatorHelper.passwordValidator("1234567"))
        assertTrue(ValidatorHelper.passwordValidator("aefbt12"))
        assertFalse(ValidatorHelper.passwordValidator("12345"))
        assertFalse(ValidatorHelper.passwordValidator("ertfd"))
        assertFalse(ValidatorHelper.passwordValidator(" "))
    }


}