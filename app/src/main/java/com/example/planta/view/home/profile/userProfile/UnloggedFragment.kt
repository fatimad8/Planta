package com.example.planta.view.home.profile.userProfile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.planta.R
import com.example.planta.view.login.LoginActivity
import com.example.planta.view.register.RegisterActivity


class UnloggedFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v= inflater.inflate(R.layout.fragment_unlogged, container, false)
        var login=v.findViewById<TextView>(R.id.textViewLoginHere)
        var signUp=v.findViewById<Button>(R.id.buttonSignupWith)

        login.setOnClickListener {
            startActivity(Intent(context, LoginActivity::class.java))
        }

        signUp.setOnClickListener {
            startActivity(Intent(context, RegisterActivity::class.java))

        }
        return v
    }


}