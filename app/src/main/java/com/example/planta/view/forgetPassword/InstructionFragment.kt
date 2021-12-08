package com.example.planta.view.forgetPassword

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.planta.R
import com.example.planta.view.login.LoginActivity

class InstructionFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v=  inflater.inflate(R.layout.fragment_instruction, container, false)
        var back = v.findViewById<TextView>(R.id.textViewBack)
        var tryagin= v.findViewById<TextView>(R.id.textViewTryAgain)

        back.setOnClickListener {
            startActivity(Intent(context,LoginActivity::class.java))
        }

        tryagin.setOnClickListener {
            startActivity(Intent(context,forgetPassActivity::class.java))
        }

        return v
    }

    companion object {

    }
}