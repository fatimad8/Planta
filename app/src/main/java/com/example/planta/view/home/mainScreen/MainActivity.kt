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
import com.example.planta.view.notification.Notification
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    lateinit var mToolbar:Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var mTablayout = findViewById<TabLayout>(R.id.mTabLayout)
        var mViewPager = findViewById<ViewPager2>(R.id.mViewPager)
        mViewPager.adapter= HomeAdapter(this)


        mToolbar = findViewById<Toolbar>(R.id.mtoolbar2)
        mToolbar.title=getString(R.string.planta)
        mToolbar.setTitleTextColor(Color.WHITE)

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.profile_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.language ->{

                if(mToolbar.title=="Planta"){
                    LocalizationUtil.changeLanguage(this,"ar")
                    SharedPreferencesHelper.saveLanguage(this,"ar")
                }

                else{
                    LocalizationUtil.changeLanguage(this,"en")
                    SharedPreferencesHelper.saveLanguage(this,"en")

                }

            }
        }
        return super.onOptionsItemSelected(item)
    }


}
