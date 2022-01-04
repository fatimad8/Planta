package com.example.planta.view.home.profile.orderHistory

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.planta.R
import com.example.planta.model.History

class OrderDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_details)
        var oRecyclerView=findViewById<RecyclerView>(R.id.oRecyclerView)
        var date=findViewById<TextView>(R.id.OrderDate)
        var ordernumber=findViewById<TextView>(R.id.OrderId)
        var orderTotalPrice=findViewById<TextView>(R.id.OrderPrice)
        var oToolbar=findViewById<androidx.appcompat.widget.Toolbar>(R.id.oToolbar)
        var shippedTo=findViewById<TextView>(R.id.textViewShipped)

        oToolbar.title=getString(R.string.order_history)
        oToolbar.setTitleTextColor(Color.WHITE)

        setSupportActionBar(oToolbar)
        oToolbar.setNavigationOnClickListener {
            finish()
        }


        var history=intent.getSerializableExtra("OrderHistory") as History

        date.text=history.createdAt
        ordernumber.text=history.id
        orderTotalPrice.text=history.total_price.toString()+" SR"
        shippedTo.text=history.Address

        oRecyclerView.layoutManager=GridLayoutManager(this,1)

        OrderHistoryViewModel().getOrderItem(history.uesrId,history.id).observeForever {

            oRecyclerView.adapter=HistoryDetailsAdapter(it)
        }




    }
}