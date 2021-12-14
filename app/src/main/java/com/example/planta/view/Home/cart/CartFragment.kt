package com.example.planta.view.Home.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.planta.R
import com.example.planta.model.Product
import com.example.planta.view.cart.CartAdapter
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CartFragment : Fragment() {


    val vm: CartViewModel by viewModels()
    var auth = Firebase.auth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var pIdList = mutableListOf<String>()
        var pid: String = ""
        var itemList = mutableListOf<String>()
        var v = inflater.inflate(R.layout.fragment_cart, container, false)
        var cRecylerView = v.findViewById<RecyclerView>(R.id.cartRecylerView)
        cRecylerView.layoutManager = LinearLayoutManager(context)
        var uid = auth.currentUser?.uid
        if (uid != null) {
            vm.getUserCart(uid).observeForever {
                it
                print("cart:$it")

                for (p in it) {
                    pIdList.add(p.pId.toString())
                    print("product id:$pIdList")

                }
                var listOfItems = listOf<Product>()
                for(item in pIdList){
                    vm.getCartItem(item).observeForever{
                        listOfItems = it
//                        cRecylerView.adapter = CartAdapter(it)
                    }
                    cRecylerView.adapter = CartAdapter(listOfItems)


                }


//                for (item in pIdList) {
//                    print("item:$item")
//                    itemList.add(item)
//                    vm.getCartItem(item).observeForever {
//                        cRecylerView.adapter = CartAdapter(it)
//                    }
            }

        }
        return v
    }


}





