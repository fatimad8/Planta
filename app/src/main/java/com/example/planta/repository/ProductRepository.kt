package com.example.planta.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.planta.model.Product
import com.example.planta.network.API
import com.example.planta.network.ProductService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductRepository {

    val prdouctService = API.getInstence().create(ProductService::class.java)





    fun getAllProduct(): MutableLiveData<List<Product>>{

        var mutableLiveData=MutableLiveData<List<Product>>()


         prdouctService.getAllProducts()
           .enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                mutableLiveData.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
        return mutableLiveData
    }


    fun getIndoor(): LiveData<List<Product>> {
        var mutableLiveData = MutableLiveData<List<Product>>()

        prdouctService.getIndoor()
            .enqueue(object : Callback<List<Product>> {
                override fun onResponse(
                    call: Call<List<Product>>,
                    response: Response<List<Product>>
                ) {
                    mutableLiveData.postValue(response.body())
                }

                override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        return mutableLiveData
    }


    fun getOutdoor(): LiveData<List<Product>> {
        var mutableLiveData = MutableLiveData<List<Product>>()

        prdouctService.getOutndoor()
            .enqueue(object : Callback<List<Product>> {
                override fun onResponse(
                    call: Call<List<Product>>,
                    response: Response<List<Product>>
                ) {
                    mutableLiveData.postValue(response.body())
                }

                override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        return mutableLiveData
    }


    fun getAccessories(): LiveData<List<Product>> {
        var mutableLiveData = MutableLiveData<List<Product>>()

        prdouctService.getAccessories()
            .enqueue(object : Callback<List<Product>> {
                override fun onResponse(
                    call: Call<List<Product>>,
                    response: Response<List<Product>>
                ) {
                    mutableLiveData.postValue(response.body())
                }

                override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        return mutableLiveData
    }
}
