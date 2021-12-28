package com.example.planta.view.home.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.planta.model.Product
import com.example.planta.repository.ProductRepository

class SearchViewModel:ViewModel() {

    var filteredData=MutableLiveData<List<Product>>()


    fun search(key: String):LiveData<List<Product>>{
      filteredData= ProductRepository().search(key)
        return filteredData

    }

}