package com.example.planta.view.product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.planta.R

class OutdoorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_outdoor)


        val vm: productViewModel by viewModels()


        var mRecyclerView = findViewById<RecyclerView>(R.id.outRecylerView)
        mRecyclerView.layoutManager= GridLayoutManager(this,2)

        vm.getOutdoor().observe(this,{list->
            mRecyclerView.adapter=ProductAdapter(list)
        })

    }
}