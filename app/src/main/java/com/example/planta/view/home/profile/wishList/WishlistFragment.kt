package com.example.planta.view.home.profile.wishList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.planta.R
import com.example.planta.util.SharedPreferencesHelper


class WishlistFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v= inflater.inflate(R.layout.fragment_wishlist, container, false)
        var wRecyclerView=v.findViewById<RecyclerView>(R.id.wishlistRecylerView)
        wRecyclerView.layoutManager=GridLayoutManager(requireContext(),2)
        var uid=SharedPreferencesHelper.getUserId(requireContext())
        var wid=SharedPreferencesHelper.getWishListId(requireContext())
        WishListViewModel().getUserWishlist(uid, wid).observe(viewLifecycleOwner,{
            if(it!=null)
                wRecyclerView.adapter=WishlistAdapter(it)

        })




        return v
    }


}