package com.example.planta.view.product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.planta.R

class IndoorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_indoor)

        val vm: productViewModel by viewModels()


        var mRecyclerView = findViewById<RecyclerView>(R.id.inRecyclerView)
        mRecyclerView.layoutManager=GridLayoutManager(this,2)

        vm.getIndoor().observe(this,{list->
            mRecyclerView.adapter=ProductAdapter(list)
        })






    }
}