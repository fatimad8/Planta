package com.example.planta.view.home.cart

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context.LOCATION_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.location.*
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.planta.R
import com.example.planta.util.SharedPreferencesHelper
import com.example.planta.view.cart.CartAdapter
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.planta.model.Cart
import com.example.planta.model.History
import com.example.planta.model.HistoryItem
import com.example.planta.model.Item
import com.example.planta.view.home.profile.orderHistory.OrderHistoryViewModel
import com.example.planta.view.location.ShippingLocationActivity
import okhttp3.internal.notifyAll
import java.time.LocalDate
import java.util.*

private const val TAG = "CartFragment"
class CartFragment : Fragment() {


    val vm: CartViewModel by activityViewModels()
    var auth = Firebase.auth
    val listInCart = mutableListOf<Item>()
    val supportFragmentManager = activity?.supportFragmentManager?.beginTransaction()
    var emptyCart = EmptyCartFragment()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(v: View, savedInstanceState: Bundle?) {
        super.onViewCreated(v, savedInstanceState)

        var uid = SharedPreferencesHelper.getUserId(context!!)
        var oid = SharedPreferencesHelper.getOrderId(context!!)

        var numOfProductTextView = v.findViewById<TextView>(R.id.textViewNumOfPro)
        var totalPriceTextView = v.findViewById<TextView>(R.id.textViewTotalPrice)
        var checkOutButton = v.findViewById<Button>(R.id.buttonCheckOut)
        var cRecylerView = v.findViewById<RecyclerView>(R.id.cartRecylerView)
        var swipeToRefresh=v.findViewById<SwipeRefreshLayout>(R.id.swiperefresh)
        var totalPrice = 0
        var totalQun = 0
        var pos = 0
        var myAdapter = CartAdapter(listInCart)
        cRecylerView.adapter = myAdapter



        swipeToRefresh.setOnRefreshListener {

            vm.getUserCart(uid, oid)
            Log.d(TAG,"user cart in refresh")
            swipeToRefresh.isRefreshing = false

        }



        cRecylerView.layoutManager = GridLayoutManager(context, 1)
        val vm: CartViewModel by viewModels()
        val vm2: OrderHistoryViewModel by viewModels()





        vm.userCart.observe(this,{
            Log.d(TAG,"Observer is called")

            if (it != null) {
                Log.d(TAG,"inside the cartFragment, it: $it")
                if (oid !="null"){
                    Log.d(TAG,"inside the cartFragment, oid: $oid")

                    listInCart.clear()
                    listInCart.addAll(it)
                    myAdapter.notifyDataSetChanged()

                    Log.d(TAG,"list in cart: $listInCart")
                    Log.d(TAG,"It: $it")

                    val swipeToDelete = object : SwipeToDeleteCallback(context!!) {
                        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                            pos = viewHolder.adapterPosition
                            var itemId = it[pos].id

                            SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                                .setTitleText(getString(R.string.are_you_sure))
                                .setConfirmText(getString(R.string.yes_delete_it))
                                .setConfirmClickListener { sDialog ->
                                    sDialog
                                        .setTitleText(getString(R.string.deleted))
                                        .setConfirmText(getString(R.string.ok))
                                        .setConfirmClickListener(null)
                                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE)

                                    vm.deleteItem(uid, oid, itemId).observe(viewLifecycleOwner, {

                                        if (it) {
                                            sDialog
                                                .setTitleText(R.string.deleted)
                                                .setConfirmText(getString(R.string.ok))
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

                    for (p in it) {
                        var itemPrice = p.price.substringBefore(" ").toInt()
                        totalPrice += itemPrice * p.quantity
                        totalQun += p.quantity
                        myAdapter.notifyDataSetChanged()
                    }

                    numOfProductTextView.text = "x" + totalQun.toString()
                    Log.d(TAG,"total price and quntit")
                    totalPriceTextView.text = "${totalPrice} SR"
                    myAdapter.notifyDataSetChanged()
                    Log.d(TAG,"Fragment called again")

                }

            }


        })
        vm.getUserCart(uid, oid)


        checkOutButton.setOnClickListener {

            var i = Intent(context, ShippingLocationActivity::class.java)
            i.putExtra("totalPrice", totalPrice)
            startActivity(i)
        }

    }

}









