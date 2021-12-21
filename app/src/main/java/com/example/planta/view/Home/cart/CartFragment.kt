package com.example.planta.view.Home.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.planta.R
import com.example.planta.model.Product
import com.example.planta.util.SharedPreferencesHelper
import com.example.planta.view.cart.CartAdapter
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CartFragment : Fragment() {


    val vm: CartViewModel by viewModels()
    var auth = Firebase.auth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var v = inflater.inflate(R.layout.fragment_cart, container, false)
        var cRecylerView = v.findViewById<RecyclerView>(R.id.cartRecylerView)
        cRecylerView.layoutManager = GridLayoutManager(context,1)
        val vm:CartViewModel by viewModels()
        var uid= SharedPreferencesHelper.getUserId(context!!)
        var odi= SharedPreferencesHelper.getOrderId(context!!)
        vm.getUserCart(uid,odi).observeForever {
            cRecylerView.adapter=CartAdapter(it)
        }

        return v
        }

    }








