package com.example.planta.view.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.planta.model.Product
import com.example.planta.repository.ProductRepository

class productViewModel : ViewModel() {
    var productRepository=ProductRepository()



    fun getProducts(): LiveData<List<Product>> {
        return productRepository.getAllProduct()
    }

    fun getIndoor():LiveData<List<Product>> {
        return productRepository.getIndoor()
    }

    fun getOutdoor():LiveData<List<Product>> {
        return productRepository.getOutdoor()
    }


    fun getAccessories():LiveData<List<Product>> {
        return productRepository.getAccessories()
    }


}