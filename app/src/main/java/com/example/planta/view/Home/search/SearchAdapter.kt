package com.example.planta.view.Home.search

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.planta.R
import com.example.planta.model.Product
import com.example.planta.view.details.DetailsActivity
import com.squareup.picasso.Picasso

class SearchAdapter1(var data: List<Product>) : RecyclerView.Adapter<SearchAdapterHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapterHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.raw_search_item, null)
        return SearchAdapterHolder(v)
    }

    override fun onBindViewHolder(holder: SearchAdapterHolder, position: Int) {
        holder.searchName.text = data[position].name
        holder.searchPrice.text = data[position].price
        Picasso.get().load(data[position].photo).into(holder.searchImage)

        holder.searchCard.setOnClickListener {
            var intent = Intent(holder.itemView.context, DetailsActivity::class.java)
            intent.putExtra("product", data[position])
            holder.itemView.context.startActivity(intent)
        }

    }


    override fun getItemCount(): Int {
        return data.size
    }
}

class SearchAdapterHolder(v: View) : RecyclerView.ViewHolder(v) {
    var searchName = v.findViewById<TextView>(R.id.textViewSearchName)
    var searchPrice = v.findViewById<TextView>(R.id.textViewSearchPrice)
    var searchImage = v.findViewById<ImageView>(R.id.imageViewSearch)
    var searchCard = v.findViewById<CardView>(R.id.searchCard)
}