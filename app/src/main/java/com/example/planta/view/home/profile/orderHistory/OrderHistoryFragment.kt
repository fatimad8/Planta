package com.example.planta.view.home.profile.orderHistory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.planta.R
import com.example.planta.util.SharedPreferencesHelper

class OrderHistoryFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v= inflater.inflate(R.layout.fragment_order_history, container, false)

        var uid=SharedPreferencesHelper.getUserId(requireContext())
        var orderRecylerView=v.findViewById<RecyclerView>(R.id.orderRecylerView)
        orderRecylerView.layoutManager=GridLayoutManager(context,1)
        OrderHistoryViewModel().getOrderHistory(uid).observeForever {
            orderRecylerView.adapter=HistoryAdapter(it)
        }


        return v
    }


}