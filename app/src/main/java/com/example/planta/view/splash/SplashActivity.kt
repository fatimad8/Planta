package com.example.planta.view.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.planta.R
import com.example.planta.util.LocalizationUtil
import com.example.planta.util.SharedPreferencesHelper
import com.example.planta.view.home.mainScreen.MainActivity
import com.example.planta.view.notification.Notification

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            var currentLanguage= SharedPreferencesHelper.getLanguage(this)
            if (currentLanguage=="null")
                LocalizationUtil.changeLanguage(this,"en")
            else
                LocalizationUtil.changeLanguage(this,currentLanguage)

            startActivity(Intent(this, MainActivity::class.java)) },2000)

    }
}