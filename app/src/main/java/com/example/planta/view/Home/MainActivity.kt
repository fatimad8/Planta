package com.example.planta.view.Home

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.planta.R
import com.example.planta.view.forgetPassword.ResetFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var mTablayout = findViewById<TabLayout>(R.id.mTabLayout)
        var mViewPager = findViewById<ViewPager2>(R.id.mViewPager)
        mViewPager.adapter= HomeAdapter(this)


        val mToolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.mtoolbar2)
        mToolbar.title="Planta"
        mToolbar.setTitleTextColor(Color.WHITE)


        mToolbar.setNavigationOnClickListener {
            finish()
        }

        setSupportActionBar(mToolbar)

        var icon = arrayOf(
            R.drawable.ic_baseline_home_24,
            R.drawable.ic_baseline_search_24,
            R.drawable.ic_baseline_shopping_cart_24,
            R.drawable.ic_baseline_person_24
        )
        TabLayoutMediator(mTablayout, mViewPager) { tab, postion ->

            tab.setIcon(icon[postion])

        }.attach()

    }
}
