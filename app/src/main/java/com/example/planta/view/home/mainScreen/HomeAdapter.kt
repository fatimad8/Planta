package com.example.planta.view.home.mainScreen

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.planta.R
import com.example.planta.view.home.cart.CartFragment
import com.example.planta.view.home.cart.CartHolderFragment
import com.example.planta.view.home.profile.userProfile.LoggedFragment
import com.example.planta.view.home.profile.userProfile.ProfileFragment
import com.example.planta.view.home.profile.userProfile.ProfileViewModel
import com.example.planta.view.home.profile.userProfile.UnloggedFragment
import com.example.planta.view.home.search.SearchFragment
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
            2 -> return CartHolderFragment()
            3 -> return ProfileFragment()

        }
            return HomeFragment()
        }
    }


