package com.example.planta.view.Home.MainScreen

import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.planta.R
import com.example.planta.view.Home.cart.CartFragment
import com.example.planta.view.Home.profile.LoggedFragment
import com.example.planta.view.Home.profile.ProfileFragment
import com.example.planta.view.Home.profile.ProfileViewModel
import com.example.planta.view.Home.profile.UnloggedFragment
import com.example.planta.view.Home.search.SearchFragment
import com.example.planta.view.login.loginViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    //val vm: ProfileViewModel by viewMo

    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return HomeFragment()
            1 -> return SearchFragment()
            2 -> return CartFragment()
            3 -> return ProfileFragment()

        }
            return HomeFragment()
        }
    }


    fun check(): Boolean {
        var flag =false
        ProfileViewModel().checkLogin(Firebase.auth.currentUser).observeForever {
            if(it){
                flag=true
            }else{
                flag=false
            }
        }
        return flag
    }

