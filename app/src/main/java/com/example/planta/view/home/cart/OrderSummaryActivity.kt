package com.example.planta.view.home.cart

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.planta.R
import com.example.planta.model.History
import com.example.planta.model.HistoryItem
import com.example.planta.util.SharedPreferencesHelper
import com.example.planta.view.home.mainScreen.MainActivity
import com.example.planta.view.home.profile.orderHistory.HistoryDetailsAdapter
import com.example.planta.view.home.profile.orderHistory.OrderHistoryViewModel
import com.example.planta.view.location.AddressViewModel
import java.time.LocalDate

class OrderSummaryActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_summary)

        var uid= SharedPreferencesHelper.getUserId(this)
        var oid= SharedPreferencesHelper.getOrderId(this)

        val vm: CartViewModel by viewModels()
        val vm2: OrderHistoryViewModel by viewModels()
        val vm3: AddressViewModel by viewModels()

        var summaryRecyclerView=findViewById<RecyclerView>(R.id.summaryRecyclerView)
        summaryRecyclerView.layoutManager=GridLayoutManager(this,1)


        vm.getUserCart(uid,oid).observeForever {
            summaryRecyclerView.adapter=OrderSummaryAdapter(it)
        }



        var toolbar=findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        toolbar.title=getString(R.string.CheckOut)
        toolbar.setTitleTextColor(Color.WHITE)

        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        var subTotal=findViewById<TextView>(R.id.textViewSubTotal)
        var summaryPrice=findViewById<TextView>(R.id.textViewSummaryPrice)
        var summaryAddress=findViewById<TextView>(R.id.textViewSummaryAddress)
        var buyButton=findViewById<Button>(R.id.buttonBuyNow)




        var totalPrice=intent.getIntExtra("totalPrice",0)
        var address=SharedPreferencesHelper.getAddress(this)
        subTotal.text=totalPrice.toString()+"SR"
        summaryPrice.text=totalPrice.toString()+"SR"
        summaryAddress.text=address




        buyButton.setOnClickListener {
            vm2.createOrderHistory(uid, History(LocalDate.now().toString(), "", totalPrice, uid,address!!))
                .observeForever { newHistory ->
                    if (it != null) {
                        Toast.makeText(this, getString(R.string.item_added), Toast.LENGTH_SHORT).show()
                        vm.getUserCart(uid, oid).observeForever { cartItems ->
                            if (cartItems != null) {
                                for (item in cartItems) {

                                    vm2.addOrderToHistory(
                                        uid,
                                        newHistory.id,
                                        HistoryItem(
                                            newHistory.id,
                                            "",
                                            item.name,
                                            item.photo,
                                            item.price,
                                            item.quantity
                                        )
                                    )
                                        .observeForever {
                                            if (it) {
                                                SweetAlertDialog(
                                                                this,
                                                                SweetAlertDialog.SUCCESS_TYPE
                                                            )
                                                                .setTitleText(getString(R.string.order_complete))
                                                                .setConfirmText(getString(R.string.ok))
                                                                .setConfirmClickListener {
                                                                    startActivity(
                                                                        Intent(
                                                                            this,
                                                                            MainActivity::class.java
                                                                        )
                                                                    )

                                                                }.show()
                                                        }

//                                                vm.deleteUserCart(uid, oid)
//                                                    .observeForever {
//                                                        if(it){
//                                                            SweetAlertDialog(
//                                                                this,
//                                                                SweetAlertDialog.SUCCESS_TYPE
//                                                            )
//                                                                .setTitleText(getString(R.string.order_complete))
//                                                                .setConfirmText(getString(R.string.ok))
//                                                                .setConfirmClickListener {
//                                                                    startActivity(
//                                                                        Intent(
//                                                                            this,
//                                                                            MainActivity::class.java
//                                                                        )
//                                                                    )
//
//                                                                }.show()
//                                                        }
                                                        SharedPreferencesHelper.saveOrderId(this,"null")

                                                    }
                                            }
                                        }
                                }
                            }
                        }

                    }
                }




        }




