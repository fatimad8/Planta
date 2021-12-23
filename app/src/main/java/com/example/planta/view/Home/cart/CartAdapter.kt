package com.example.planta.view.cart

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.planta.R
import com.example.planta.model.Cart
import com.example.planta.model.Item
import com.example.planta.model.Product
import com.example.planta.util.SharedPreferencesHelper
import com.example.planta.view.Home.cart.CartViewModel
import com.example.planta.view.Home.cart.SwipeToDeleteCallback
import com.example.planta.view.details.DetailsActivity
import com.squareup.picasso.Picasso

class CartAdapter(var data: List<Item>) : RecyclerView.Adapter<CartAdapterHolder>() {

    private var listData: MutableList<Item> = data as MutableList<Item>
    var selectedList = mutableListOf<Int>()
    lateinit var swipe: SwipeToDeleteCallback
    var itemId: String = ""
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapterHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.raw_cart_item, null)
        return CartAdapterHolder(v)
    }

    override fun onBindViewHolder(holder: CartAdapterHolder, @SuppressLint("RecyclerView") position: Int) {

        holder.proName.text = data[position].name
        println("name:" + data[position].name)
        holder.price.text = data[position].price.toString()
        Picasso.get().load(data[position].photo).into(holder.cartPhoto)


        val quntList = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        var adapter = ArrayAdapter<Int>(
            holder.spinner.context,
            android.R.layout.simple_spinner_item,
            quntList
        )
        holder.spinner.adapter = adapter
        var selectedQuant = data[position].quantity
        if (selectedQuant != null) {
            var pos = adapter.getPosition(selectedQuant)
            holder.spinner.setSelection(pos)
        }


        holder.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                var qun = parent.getItemAtPosition(pos)
                var uid = SharedPreferencesHelper.getUserId(holder.cartCard.context)
                var oid = SharedPreferencesHelper.getOrderId(holder.cartCard.context)
                var itemId = data[position].id
                var items = Item(
                    data[position].orderId,
                    data[position].category,
                    data[position].createdAt,
                    data[position].description,
                    data[position].id,
                    data[position].name,
                    data[position].photo,
                    data[position].price,
                    qun as Int
                )
                CartViewModel().updateCartQun(uid, oid, itemId, items)
                println("item:$qun")


            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }


            //        holder.cartCard.setOnClickListener {
//            var intent = Intent(holder.itemView.context, DetailsActivity::class.java)
//            intent.putExtra("item", data[position])
//
//            holder.itemView.context.startActivity(intent)
//        }


    }


    //holder.spinner.=data[position].quantity


    override fun getItemCount(): Int {
        return data.size
    }


    fun deleteItem(index: Int) {
        listData.removeAt(index)
        notifyDataSetChanged()
    }

}

class CartAdapterHolder(v: View) : RecyclerView.ViewHolder(v) {

    var cartCard = v.findViewById<CardView>(R.id.cartCardView)
    var spinner = v.findViewById<Spinner>(R.id.spinnerCart)
    var proName = v.findViewById<TextView>(R.id.textViewCartProName)
    var price = v.findViewById<TextView>(R.id.textViewCartPrice)
    var cartPhoto = v.findViewById<ImageView>(R.id.imageViewCartPhoto)


}