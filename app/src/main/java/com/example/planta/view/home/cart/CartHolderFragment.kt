package com.example.planta.view.home.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.planta.R
import com.example.planta.util.SharedPreferencesHelper
import com.example.planta.view.home.profile.userProfile.ProfileViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import androidx.fragment.app.FragmentTransaction


class CartHolderFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v=  inflater.inflate(R.layout.fragment_cart_holder, container, false)
        val supportFragmentManager2 = activity?.supportFragmentManager?.beginTransaction()

        val manager = childFragmentManager
        val transaction: FragmentTransaction = manager.beginTransaction()

        var emptyCart=EmptyCartFragment()
        var cart=CartFragment()
        var auth= Firebase.auth
        var user= auth.currentUser


        ProfileViewModel().checkLogin(user).observeForever {
            if(it){

                CartViewModel().getUserCart(
                    SharedPreferencesHelper.getUserId(requireContext()),
                    SharedPreferencesHelper.getOrderId(requireContext())).observeForever {
                    if(it.isNullOrEmpty()){
                        transaction.replace(R.id.cFrameLayout,emptyCart).commit()
                    }else{
                        transaction.replace(R.id.cFrameLayout,cart).commit()
                    }
                }
            }else{
                transaction
                    .replace(R.id.cFrameLayout,emptyCart).commit()
            }

        }
        return v
    }


}