package com.example.planta.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import com.example.planta.R
import com.example.planta.view.Home.MainActivity
import com.example.planta.view.forgetPassword.forgetPassActivity
import com.example.planta.view.register.RegisterActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val buttonLogin = findViewById<Button>(R.id.buttonLogin)
        val emailEditText = findViewById<EditText>(R.id.editTextLogEmail)
        val passEditText = findViewById<EditText>(R.id.editTextLogPass)
        val signUp = findViewById<TextView>(R.id.textViewSignUp)
        val forgetPass = findViewById<TextView>(R.id.textViewForgetPass)

        val vm: loginViewModel by viewModels()


        buttonLogin.setOnClickListener {
            if(emailEditText.text.isEmpty()||passEditText.text.isEmpty()){
                Toast.makeText(this, "Please Fill all Fields", Toast.LENGTH_SHORT).show()
            }else{
                vm.sign(emailEditText.text.toString(), passEditText.text.toString())
                    .observe(this, {
                        println("result:$it")
                        if (it) {
                            startActivity(Intent(this, MainActivity::class.java))
                            Toast.makeText(this, "Login Success", Toast.LENGTH_LONG).show()

                        } else {
                            Toast.makeText(this, "wrong username & password!", Toast.LENGTH_SHORT)
                                .show()
                        }
                    })
            }



        }
        signUp.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }


        forgetPass.setOnClickListener {
            startActivity(Intent(this, forgetPassActivity::class.java))

        }


    }
}