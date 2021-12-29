package com.example.planta.view.home.profile.orderHistory

import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.planta.R
import com.example.planta.model.History
import java.time.LocalDate
import java.util.*

class HistoryAdapter(var data: List<History>) : RecyclerView.Adapter<HistoryAdapterHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryAdapterHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.raw_history_item, null)
        return HistoryAdapterHolder(v)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: HistoryAdapterHolder, position: Int) {
        holder.orderDate.text=data[position].createdAt
        holder.orderNum.text=data[position].id
        holder.orderTotalPrice.text= data[position].total_price.toString()+" SR"
        holder.orderDeatilsCard.setOnClickListener {

            var i= Intent(holder.orderDeatilsCard.context,OrderDetailsActivity::class.java)
            i.putExtra("OrderHistory",data[position])
            holder.orderDeatilsCard.context.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

class HistoryAdapterHolder(v: View) : RecyclerView.ViewHolder(v) {
    var orderDate= v.findViewById<TextView>(R.id.textViewOrderDate)
    var orderNum= v.findViewById<TextView>(R.id.textViewOrderId)
    var orderTotalPrice= v.findViewById<TextView>(R.id.textViewOrderPrice)
    var orderDeatilsCard=v.findViewById<CardView>(R.id.orderDetailsCard)

}