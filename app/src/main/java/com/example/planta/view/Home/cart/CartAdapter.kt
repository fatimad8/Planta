package com.example.planta.view.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.planta.R
import com.example.planta.model.Cart
import com.example.planta.model.Product
import com.squareup.picasso.Picasso

class CartAdapter(var data: List<Product>) : RecyclerView.Adapter<CartAdapterHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapterHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.raw_cart_item, null)
        return CartAdapterHolder(v)
    }

    override fun onBindViewHolder(holder: CartAdapterHolder, position: Int) {

        holder.proName.text=data[position].name
        println("name:"+data[position].name)
        holder.price.text=data[position].price.toString()
        Picasso.get().load(data[position].photo).into(holder.cartPhoto)

    }

    override fun getItemCount(): Int {
        return data.size
    }
}

class CartAdapterHolder(v: View) : RecyclerView.ViewHolder(v) {

    var spinner=v.findViewById<Spinner>(R.id.spinnerCart)
    var proName= v.findViewById<TextView>(R.id.textViewCartProName)
    var price= v.findViewById<TextView>(R.id.textViewCartPrice)
    var cartPhoto= v.findViewById<ImageView>(R.id.imageViewCartPhoto)


}