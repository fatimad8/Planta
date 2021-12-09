package com.example.planta.view.product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.planta.R
import com.example.planta.model.Product

class IndoorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_indoor)
        var mRecyclerView = findViewById<RecyclerView>(R.id.mRecyclerView)
        val vm: productViewModel by viewModels()
        vm.getIndoor("Indoor").observe(this,{list->
            mRecyclerView.adapter=ProductAdapter(list)

        })



    }
}