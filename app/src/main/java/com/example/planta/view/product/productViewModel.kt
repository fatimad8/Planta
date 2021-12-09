package com.example.planta.view.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.planta.model.Product
import com.example.planta.repository.ProductRepository

class productViewModel : ViewModel() {

    fun getIndoor(category: String): LiveData<List<Product>> {
        var mLiveData = MutableLiveData<List<Product>>()
        ProductRepository().getIndoor(category)
        return mLiveData
    }
}