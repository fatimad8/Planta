package com.example.planta.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import com.example.planta.R
import com.example.planta.view.MainActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var buttonLogin=findViewById<Button>(R.id.buttonLogin)
        var emailEditText=findViewById<EditText>(R.id.editTextLogEmail)
        var passEditText= findViewById<EditText>(R.id.editTextLogPass)
        val vm:loginViewModel by viewModels()


        buttonLogin.setOnClickListener {
            vm.sign(emailEditText.text.toString(),passEditText.text.toString())
                .observe(this,{
                    if(it){
                        startActivity(Intent(this, MainActivity::class.java))
                        Toast.makeText(this,"Login Success",Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this, "wrong username & password!", Toast.LENGTH_SHORT).show()
                    }
                })

        }
    }
}