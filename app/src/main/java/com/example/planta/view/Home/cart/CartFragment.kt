package com.example.planta.view.Home.cart

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.planta.R
import com.example.planta.model.Product
import com.example.planta.util.SharedPreferencesHelper
import com.example.planta.view.cart.CartAdapter
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import cn.pedant.SweetAlert.SweetAlertDialog
import cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener


class CartFragment : Fragment() {


    val vm: CartViewModel by viewModels()
    var auth = Firebase.auth
    private lateinit var myAdapter: CartAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var v = inflater.inflate(R.layout.fragment_cart, container, false)
        var numOfProductTextView = v.findViewById<TextView>(R.id.textViewNumOfPro)
        var totalPriceTextView = v.findViewById<TextView>(R.id.textViewTotalPrice)
        var checkOutButton = v.findViewById<Button>(R.id.buttonCheckOut)
        var cRecylerView = v.findViewById<RecyclerView>(R.id.cartRecylerView)
        var totalPrice = 0
        var totalQun = 0
        var pos = 0
        cRecylerView.layoutManager = GridLayoutManager(context, 1)
        val vm: CartViewModel by viewModels()
        var uid = SharedPreferencesHelper.getUserId(context!!)
        var oid = SharedPreferencesHelper.getOrderId(context!!)
        //var itemId = ""

        vm.getUserCart(uid, oid).observeForever {
            if (it != null) {
                var myAdapter = CartAdapter(it)
                cRecylerView.adapter = myAdapter
                //itemId=myAdapter.getID()
                val swipeToDelete = object : SwipeToDeleteCallback(context!!) {
                    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                        pos = viewHolder.adapterPosition
                        var itemId = it[pos].id

                        SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Are you sure?")
                            .setConfirmText("Yes,delete it!")
                            .setConfirmClickListener { sDialog ->
                                sDialog
                                    .setTitleText("Deleted!")
                                    .setConfirmText("OK")
                                    .setConfirmClickListener(null)
                                    .changeAlertType(SweetAlertDialog.SUCCESS_TYPE)

                                vm.deleteItem(uid, oid, itemId).observe(viewLifecycleOwner, {

                                    if (it) {
                                        sDialog
                                            .setTitleText("Deleted!")
                                            .setConfirmText("OK")
                                            .setConfirmClickListener(null)
                                            .changeAlertType(SweetAlertDialog.SUCCESS_TYPE)
                                    }

                                    myAdapter.deleteItem(pos)

                                })

                            }
                            .show()

                    }
                }
                val touchHelper = ItemTouchHelper(swipeToDelete)
                touchHelper.attachToRecyclerView(cRecylerView)

            }
            for (p in it) {
                var itemPrice = p.price.substringBefore(" ").toInt()
                totalPrice += itemPrice * p.quantity
                totalQun += p.quantity

            }

            numOfProductTextView.text = "x" + totalQun.toString()
            totalPriceTextView.text = "${totalPrice} SR"

        }
        checkOutButton.setOnClickListener {

        }


        return v
    }


}









