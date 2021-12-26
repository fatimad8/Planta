package com.example.planta.view.Home.cart

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.planta.R
import com.example.planta.view.Home.MainScreen.MainActivity


class EmptyCartFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v= inflater.inflate(R.layout.fragment_empty_cart, container, false)
        var start=v.findViewById<Button>(R.id.buttonStart)

        start.setOnClickListener {
            startActivity(Intent(Intent(context,MainActivity::class.java)))
         }
        return v
    }


}