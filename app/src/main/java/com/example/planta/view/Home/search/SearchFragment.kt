package com.example.planta.view.Home.search

import android.app.Activity
import android.os.Bundle
import android.view.*
import android.view.View.inflate
import androidx.fragment.app.Fragment
import android.widget.AdapterView
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.core.app.NavUtils
import androidx.core.graphics.drawable.DrawableCompat.inflate
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.planta.R
import com.example.planta.model.Product
import com.example.planta.view.product.ProductAdapter
import com.example.planta.view.product.productViewModel


class SearchFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_search, container, false)
        var searchView = v.findViewById<SearchView>(R.id.mSearchView)
        var searchReclerView = v.findViewById<RecyclerView>(R.id.searchRecylerView)
        val vm: productViewModel by viewModels()

        val vm2: SearchViewModel by viewModels()
         searchReclerView.layoutManager = GridLayoutManager(context,1)


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                 vm2.search(newText).observe(viewLifecycleOwner, { list ->
                     if(list!=null){
                         searchReclerView.adapter = SearchAdapter1(list)
                     }

                })
                return true
            }
        })

        return v
    }
}




