package com.example.planta.view.product

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.planta.R
import com.example.planta.model.Product
import com.squareup.picasso.Picasso

class ProductAdapter(var data: List<Product>) : RecyclerView.Adapter<ProductAdapterHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapterHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.raw_plant_item, null)
        return ProductAdapterHolder(v)
    }

    override fun onBindViewHolder(holder: ProductAdapterHolder, position: Int) {
        holder.productName.text=data[position].name
        holder.productPrice.text=data[position].price
        holder.productStock.text=data[position].inStock
        Picasso.get().load(data[position].photo).into(holder.productImage)

    }

    override fun getItemCount(): Int {
        return data.size
    }
}

class ProductAdapterHolder(v: View) : RecyclerView.ViewHolder(v) {
    var productName=v.findViewById<TextView>(R.id.textViewProductName)
    var productPrice=v.findViewById<TextView>(R.id.textViewProductName)
    var productStock=v.findViewById<TextView>(R.id.textViewInStock)
    var productImage=v.findViewById<ImageView>(R.id.imageViewProduct)
}