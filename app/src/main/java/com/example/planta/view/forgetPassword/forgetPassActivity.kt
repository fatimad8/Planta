package com.example.planta.view.forgetPassword

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.Toolbar
import androidx.activity.viewModels
import com.example.planta.R
import com.example.planta.view.login.loginViewModel

class forgetPassActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_pass2)



        val mToolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.mtoolbar)
        mToolbar.setTitleTextColor(Color.WHITE)

        val restPass=ResetFragment()
        setSupportActionBar(mToolbar)

        mToolbar.setNavigationOnClickListener {
            finish()
        }



        supportFragmentManager.beginTransaction()
            .replace(R.id.mFrameLayout,restPass)
            .commit()





    }
}
