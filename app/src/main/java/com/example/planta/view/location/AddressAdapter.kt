package com.example.planta.view.location

import android.content.Intent
import android.location.Address
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.planta.R
import com.example.planta.util.SharedPreferencesHelper
import com.example.planta.view.home.cart.OrderSummaryActivity
import okhttp3.internal.notifyAll


var mSelectedItem = -1

class AddressAdapter(var data: List<com.example.planta.model.Address>) : RecyclerView.Adapter<AddressAdapterHolder>() {
     var addr:String=" "
     var rbChecked: RadioButton? = null
     var rbPosoition = 0
     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressAdapterHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.raw_address, null)
        return AddressAdapterHolder(v)
    }

    override fun onBindViewHolder(holder: AddressAdapterHolder, position: Int) {
        holder.address.text="${data[position].Country}, ${data[position].State}, ${data[position].City}, ${data[position].postalCode}"


    holder.selected.setOnCheckedChangeListener { compoundButton, b ->
        if (rbChecked !== null) {
            rbChecked!!.isChecked = false
        }
        rbChecked = holder.selected
          addr="${data[holder.adapterPosition].Country}, ${data[holder.adapterPosition].State},  ${data[holder.adapterPosition].City},  ${data[holder.adapterPosition].postalCode}"
          SharedPreferencesHelper.saveAddress(holder.address.context,addr)
    }

//                 addr="${data[position].Country}, ${data[position].State}, ${data[position].City}, ${data[position].postalCode}"
//                val intent= Intent(holder.addressBox.context,OrderSummaryActivity::class.java)
//                intent.putExtra("address",addr)
//                holder.addressBox.context.startActivity(intent)

            }







    override fun getItemCount(): Int {
        return data.size
    }

    fun getAddress():String{
        return addr
    }


}

class AddressAdapterHolder(v: View) : RecyclerView.ViewHolder(v) {
    var address = v.findViewById<TextView>(R.id.textViewAddress)
    var selected = v.findViewById<RadioButton>(R.id.addressRadioButton)
}
