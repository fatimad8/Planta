package com.example.planta.view.home.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.planta.R
import com.example.planta.model.Item
import com.squareup.picasso.Picasso

class OrderSummaryAdapter(var data: List<Item>) :
    RecyclerView.Adapter<OrderSummaryAdapterHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderSummaryAdapterHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.history_details, null)
        return OrderSummaryAdapterHolder(v)
    }

    override fun onBindViewHolder(holder: OrderSummaryAdapterHolder, position: Int) {
        holder.orderName.text=data[position].name
        holder.itemPrice.text=data[position].price.toString()
        holder.orderQun.text="x"+data[position].quantity.toString()
        Picasso.get().load(data[position].photo).into(holder.orderImage)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

class OrderSummaryAdapterHolder(v: View) : RecyclerView.ViewHolder(v) {
    var orderImage=v.findViewById<ImageView>(R.id.imageViewOrderDet)
    var orderName= v.findViewById<TextView>(R.id.textViewOrderName)
    var orderQun=v.findViewById<TextView>(R.id.textViewOrderQun)
    var itemPrice=v.findViewById<TextView>(R.id.textViewOrderDetPrice)

}