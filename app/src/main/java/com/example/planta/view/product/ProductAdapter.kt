package com.example.planta.view.product

import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.planta.R
import com.example.planta.model.Product
import com.example.planta.view.details.DetailsActivity
import com.squareup.picasso.Picasso

class ProductAdapter(var data: List<Product>) : RecyclerView.Adapter<ProductAdapterHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapterHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.raw_plant_item, null)
        return ProductAdapterHolder(v)
    }

    override fun onBindViewHolder(holder: ProductAdapterHolder, position: Int) {
        holder.productName.text = data[position].name
        holder.productPrice.text = data[position].price
        Picasso.get().load(data[position].photo).into(holder.productImage)

        holder.productCard.setOnClickListener {
            var intent = Intent(holder.itemView.context, DetailsActivity::class.java)
            intent.putExtra("product", data[position])
            holder.itemView.context.startActivity(intent)
        }


        if (data[position].inStock == true) {
            holder.productStock.text = "In stock"
            holder.productStock.setTextColor(Color.parseColor("#6D953F"))
        } else {
            holder.productStock.text = "Out of stock"
            holder.productStock.setTextColor(Color.parseColor("#DF3D31"))
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }
}

class ProductAdapterHolder(v: View) : RecyclerView.ViewHolder(v) {
    var productName = v.findViewById<TextView>(R.id.textViewProductName)
    var productPrice = v.findViewById<TextView>(R.id.textViewProductPrice)
    var productStock = v.findViewById<TextView>(R.id.textViewInStock)
    var productImage = v.findViewById<ImageView>(R.id.imageViewProduct)
    var productCard= v.findViewById<CardView>(R.id.CardViewProduct)
}