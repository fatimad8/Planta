package com.example.planta.view.location

import android.location.Address
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.planta.R

class AddressAdapter(var data: List<com.example.planta.model.Address>) : RecyclerView.Adapter<AddressAdapterHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressAdapterHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.raw_address, null)
        return AddressAdapterHolder(v)
    }

    override fun onBindViewHolder(holder: AddressAdapterHolder, position: Int) {
         holder.address.text="${data[position].Country}, ${data[position].State}, ${data[position].City}, ${data[position].postalCode}"
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

class AddressAdapterHolder(v: View) : RecyclerView.ViewHolder(v) {
     var address=v.findViewById<TextView>(R.id.textViewAddress)
}