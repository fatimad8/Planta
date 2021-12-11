package com.example.planta.view.product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.planta.R

class AccessoriesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accessories)

        val vm: productViewModel by viewModels()


        var mRecyclerView = findViewById<RecyclerView>(R.id.accesRecylerView)
        mRecyclerView.layoutManager= GridLayoutManager(this,2)

        vm.getAccessories().observe(this,{list->
            mRecyclerView.adapter=ProductAdapter(list)
        })

    }
}