package com.example.planta.view.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import com.example.planta.R
import com.example.planta.model.User
import com.example.planta.util.SharedPreferencesHelper
import com.example.planta.util.ValidatorHelper
import com.example.planta.view.login.LoginActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val regButton = findViewById<Button>(R.id.buttonReg)
        val fname = findViewById<EditText>(R.id.editTextRegFullname)
        val email = findViewById<EditText>(R.id.editTextRegEmail)
        val pass = findViewById<EditText>(R.id.editTextRegPass)
        val login = findViewById<TextView>(R.id.textViewLogin)
        val vm: RegisterViewModel by viewModels()
        var auth = Firebase.auth


        email.addTextChangedListener {
            if(it!!.isEmpty())
                email.error="Required"
           else if(!ValidatorHelper.emailValidatore(email.text.toString()))
                email.error="Invalid email format"

        }


        pass.addTextChangedListener {
            if(it!!.isEmpty())
                pass.error="Required"
            else if(!ValidatorHelper.passwordValidator(pass.text.toString()))
                pass.error="must be at least 7"

        }


        regButton.setOnClickListener {
            if(email.text.isEmpty()||pass.text.isEmpty()||fname.text.isEmpty()){
                Toast.makeText(this, getString(R.string.Please_Fill_all_Fields), Toast.LENGTH_SHORT).show()
            }else{
                vm.register(fname.text.toString(),email.text.toString(),pass.text.toString())
                    .observe(this,{
                        if(it){
                            var user= User(auth.currentUser?.uid.toString(),"",fname.text.toString())
                                vm.addUser(user)
                                    .observe(this,{
                                        if(it){
                                            if(ValidatorHelper.emailValidatore(email.text.toString())&&ValidatorHelper.passwordValidator(pass.text.toString())){
                                                startActivity(Intent(this, LoginActivity::class.java))
                                                Toast.makeText(this,getString(R.string.register_success), Toast.LENGTH_LONG).show()
                                            }
                                        }
                                    })
                            startActivity(Intent(this, LoginActivity::class.java))
                            Toast.makeText(this,getString(R.string.register_success), Toast.LENGTH_LONG).show()
                        }else{
                            Toast.makeText(this, getString(R.string.register_failed), Toast.LENGTH_SHORT).show()
                        }
                    })
            }

        }



        login.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}