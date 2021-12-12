package com.example.planta.view.Home.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.planta.R
import com.example.planta.view.cart.CartAdapter

class CartFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v= inflater.inflate(R.layout.fragment_cart, container, false)
        var cRecylerView=v.findViewById<RecyclerView>(R.id.cartRecylerView)
        cRecylerView.layoutManager=LinearLayoutManager(context)

       // cRecylerView.adapter=CartAdapter()
        return v
    }


}