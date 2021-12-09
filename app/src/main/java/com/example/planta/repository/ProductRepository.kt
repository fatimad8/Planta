package com.example.planta.repository

import androidx.lifecycle.MutableLiveData
import com.example.planta.model.Product
import com.example.planta.network.API
import com.example.planta.network.ProductService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductRepository {

    val prdouctService = API.getInstence().create(ProductService::class.java)

    fun getIndoor(category: String): MutableLiveData<List<Product>> {
        var mLiveData = MutableLiveData<List<Product>>()
        prdouctService.getIndoor(category)
            .enqueue(object : Callback<List<Product>> {
                override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                    mLiveData.postValue(response.body())
                }

                override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        return mLiveData
    }
}
