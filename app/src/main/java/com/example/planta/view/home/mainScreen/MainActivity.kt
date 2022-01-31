package com.example.planta.view.home.mainScreen

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import com.example.planta.R
import com.example.planta.util.LocalizationUtil
import com.example.planta.util.SharedPreferencesHelper
import com.example.planta.view.home.profile.orderHistory.OrderHistoryFragment
import com.example.planta.view.home.profile.wishList.WishlistFragment
import com.example.planta.view.notification.Notification
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import androidx.fragment.app.FragmentManager
import com.example.planta.view.home.cart.CartFragment
import com.example.planta.view.home.cart.CartHolderFragment
import com.example.planta.view.home.profile.userProfile.ProfileFragment
import com.example.planta.view.home.search.SearchFragment


class MainActivity : AppCompatActivity() {
    lateinit var mToolbar:Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var mainNavgiation=findViewById<BottomNavigationView>(R.id.main_bottom_navigation)




        mToolbar = findViewById<Toolbar>(R.id.mtoolbar2)
        mToolbar.title=getString(R.string.planta)
        mToolbar.setTitleTextColor(Color.WHITE)

        setSupportActionBar(mToolbar)



        val supportFragmentManager = supportFragmentManager?.beginTransaction()
        supportFragmentManager?.replace(com.example.planta.R.id.mainFrameLayout,HomeFragment())?.commit()

        mainNavgiation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.home -> {
                    val manager: FragmentManager = getSupportFragmentManager()
                    manager.beginTransaction().replace(com.example.planta.R.id.mainFrameLayout, HomeFragment())
                        .commit()
                    true
                }
                R.id.search -> {
                    val manager: FragmentManager = getSupportFragmentManager()
                    manager.beginTransaction().replace(com.example.planta.R.id.mainFrameLayout, SearchFragment())
                        .commit()
                    true
                }

                R.id.cart->{
                    val manager: FragmentManager = getSupportFragmentManager()
                    manager.beginTransaction().replace(com.example.planta.R.id.mainFrameLayout, CartHolderFragment())
                        .commit()
                    true
                }

                R.id.profile->{
                    val manager: FragmentManager = getSupportFragmentManager()
                    manager.beginTransaction().replace(com.example.planta.R.id.mainFrameLayout, ProfileFragment())
                        .commit()
                    true
                }

                else -> true
            }
        }


    }

}
