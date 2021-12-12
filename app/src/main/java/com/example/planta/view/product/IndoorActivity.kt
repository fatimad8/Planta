package com.example.planta.view.product

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.planta.R
import com.example.planta.view.forgetPassword.ResetFragment

class IndoorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_indoor)

        val vm: productViewModel by viewModels()


        val mToolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.inToolbar)
        mToolbar.setTitle("Indoor Plant")
        mToolbar.setTitleTextColor(Color.WHITE)

        setSupportActionBar(mToolbar)

        mToolbar.setNavigationOnClickListener {
            finish()
        }




        var mRecyclerView = findViewById<RecyclerView>(R.id.inRecyclerView)
        mRecyclerView.layoutManager=GridLayoutManager(this,2)

        vm.getIndoor().observe(this,{list->
            mRecyclerView.adapter=ProductAdapter(list)
        })






    }
}