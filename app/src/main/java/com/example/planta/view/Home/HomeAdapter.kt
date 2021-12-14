package com.example.planta.view.Home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.planta.view.Home.cart.CartFragment
import com.example.planta.view.Home.profile.ProfileFragment
import com.example.planta.view.Home.search.SearchFragment

class HomeAdapter(activity: FragmentActivity): FragmentStateAdapter(activity) {


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