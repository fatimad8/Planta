package com.example.planta.view.Home.profile.WishList

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.planta.R
import com.example.planta.model.Liked
import com.example.planta.util.SharedPreferencesHelper
import com.squareup.picasso.Picasso
import xyz.hanks.library.bang.SmallBangView

class WishlistAdapter(var data: List<Liked>) : RecyclerView.Adapter<WishlistAdapterHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishlistAdapterHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.raw_wishlist_item, null)
        return WishlistAdapterHolder(v)
    }

    override fun onBindViewHolder(holder: WishlistAdapterHolder, position: Int) {
        holder.wishProName.text=data[position].name
        holder.wishProPrice.text=data[position].price
        Picasso.get().load(data[position].photo).into(holder.wishImage)

        var uid=SharedPreferencesHelper.getUserId(holder.wishImage.context)
        var wid=SharedPreferencesHelper.getWishListId(holder.wishImage.context)

        WishListViewModel().getUserWishlist(uid,wid).observeForever {
            for(p in it){
                if(p.name==data[position].name){
                    holder.wishListHeart.isSelected=true
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }
}

class WishlistAdapterHolder(v: View) : RecyclerView.ViewHolder(v) {
    var wishImage=v.findViewById<ImageView>(R.id.imageViewWishlist)
    var wishProName=v.findViewById<TextView>(R.id.productNameWish)
    var wishProPrice=v.findViewById<TextView>(R.id.prodPriceWishlist)
    val wishListHeart = v.findViewById<SmallBangView>(R.id.wishHeart)

}