package com.example.planta.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import com.example.planta.R
import com.example.planta.util.SharedPreferencesHelper
import com.example.planta.util.ValidatorHelper
import com.example.planta.view.home.mainScreen.MainActivity
import com.example.planta.view.home.cart.CartViewModel
import com.example.planta.view.home.profile.wishList.WishListViewModel
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
        val vm2: CartViewModel by viewModels()
        val vm3: WishListViewModel by viewModels()


        emailEditText.addTextChangedListener {
            if(it!!.isEmpty())
                emailEditText.error="Required"
            else if(!ValidatorHelper.emailValidatore(emailEditText.text.toString()))
                emailEditText.error="Invalid email format"

        }





        buttonLogin.setOnClickListener {
            if(emailEditText.text.isEmpty()||passEditText.text.isEmpty()){
                Toast.makeText(this, getString(R.string.Please_Fill_all_Fields), Toast.LENGTH_SHORT).show()
            }else{
                vm.sign(emailEditText.text.toString(), passEditText.text.toString())
                    .observe(this, {
                        if (it.isNotEmpty()) {
                            //if(ValidatorHelper.emailValidatore(emailEditText.text.toString()))
                            vm.getUserById(it).observe(this,{
                                var id=it[0].id
                                SharedPreferencesHelper.saveUserId(this,id)
                                startActivity(Intent(this, MainActivity::class.java))
                                Toast.makeText(this, getString(R.string.login_success), Toast.LENGTH_LONG).show()

                                    var uid=SharedPreferencesHelper.getUserId(this)
                                    vm2.getOrderId(uid,uid).observe(this,{
                                        if(it.size==0){
                                            SharedPreferencesHelper.saveOrderId(this,"null")
                                        }else{
                                            var orderid=it[0].id
                                            SharedPreferencesHelper.saveOrderId(this,orderid)
                                        }
                                    })
                                    vm3.getUserWishByUid(uid,uid).observe(this,{
                                        if(it.size==0){
                                            SharedPreferencesHelper.saveWishId(this,"null")
                                        }else{
                                            SharedPreferencesHelper.saveWishId(this,it[0].id)
                                        }
                                    })

                            })

                        } else {
                            Toast.makeText(this, getString(R.string.login_failed), Toast.LENGTH_SHORT)
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