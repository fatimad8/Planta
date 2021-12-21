package com.example.planta.view.details

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.planta.R
import com.example.planta.model.Item
import com.example.planta.model.Order
import com.example.planta.model.Product
import com.example.planta.util.SharedPreferencesHelper
import com.example.planta.view.Home.cart.CartViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import xyz.hanks.library.bang.SmallBangView
import java.time.LocalDate

class DetailsActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val vm: CartViewModel by viewModels()

        val auth = Firebase.auth


        val wishList = findViewById<SmallBangView>(R.id.like_heart)
        val close = findViewById<ImageView>(R.id.imageViewClose)
        val productPhoto = findViewById<ImageView>(R.id.imageViewDetailsPhoto)
        val prodductName = findViewById<TextView>(R.id.textViewDetName)
        val prodductPrice = findViewById<TextView>(R.id.textViewDetPrice)
        val prodductDes = findViewById<TextView>(R.id.textViewDetDesc)
        val productStock = findViewById<TextView>(R.id.textViewDetStock)
        val spinner = findViewById<Spinner>(R.id.spinner)
        val addbtn = findViewById<Button>(R.id.buttonAddCart)

        val id = SharedPreferencesHelper.getUserId(this)
        val uid = auth.currentUser?.uid
        var total_price =0
        var totalOrderPrice=0
        var oId=""
        val currentdate = LocalDate.now().toString()
        var order=Order("","",0,"","")



        val product = intent.getSerializableExtra("product") as Product


        // var product_item = intent.getSerializableExtra("item") as Item


        Picasso.get().load(product.photo).into(productPhoto)
        prodductName.text = product.name
        prodductPrice.text = product.price.toString()
        prodductDes.text = product.description
        var pId = product.id
        val price = product.price
        val date = LocalDate.now()
        var item: Any = 0


        if (product.inStock == true) {
            productStock.text = "In Stock"
            productStock.setTextColor(Color.parseColor("#6D953F"))
        } else {
            productStock.text = "Out of stock"
            productStock.setTextColor(Color.parseColor("#DF3D31"))
            addbtn.isEnabled = false

        }


        val quntList = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        spinner.adapter = ArrayAdapter<Int>(this, android.R.layout.simple_spinner_item, quntList)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                item = parent.getItemAtPosition(pos)
                println("item:$item")


            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        close.setOnClickListener {
            finish()
        }

        addbtn.setOnClickListener {

            if (SharedPreferencesHelper.getOrderId(this) == "null") {
                vm.createNewOrder(id, date.toString(), uid!!, price, item as Int).observeForever {
                    if (it != null) {
                        SharedPreferencesHelper.saveOrderId(this, it.id)
                        Toast.makeText(this, "new order", Toast.LENGTH_SHORT).show()
                        vm.addProductItem(
                            id,
                            it.id,
                            Item(
                                it.id,
                                product.category,
                                currentdate,
                                product.description,
                                "",
                                product.name,
                                product.photo,
                                product.price,
                                product.quantity
                            )
                        ).observeForever {
                            if (it)
                                Toast.makeText(this, "Item added successfully", Toast.LENGTH_SHORT)
                                    .show()
                        }


//                        var itemPrice=it.total_price.substringBefore(" ")
//                        totalOrderPrice+= Integer.valueOf(itemPrice)
////                        total_price += totalOrderPrice
//                        order=Order(it.id,it.order_date,it.quantity,totalOrderPrice.toString(),it.uesrId)
//                        vm.updateTotalPrice(id,it.id,order).observeForever {
//
//                        }
                    }

                }

            } else {
                var lastOrderId = SharedPreferencesHelper.getOrderId(this)

                vm.addProductItem(
                    id,
                    lastOrderId,
                    Item(
                        lastOrderId,
                        product.category,
                        currentdate,
                        product.description,
                        "",
                        product.name,
                        product.photo,
                        product.price,
                        product.quantity
                    )
                ).observeForever {
                    if (it)
                        Toast.makeText(this, "Item added successfully", Toast.LENGTH_SHORT)
                            .show()


//                     vm.updateTotalPrice(id,lastOrderId,order)

                }


//
//                        }

//                 var order=Order(oId)
//                var total_price = + Integer.valueOf(order.total_price)
//                vm.updateTotalPrice(id,oId,total_price)
            }

//            var id= SharedPreferencesHelper.getUserId(this)
//            var uid= auth.currentUser?.uid
//            if (uid != null) {
//                vm.addToCart(id,date.toString(),uid,price, item as Int).observeForever {
//                    if(it){
//                        Toast.makeText(this, "added successfully", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }


            wishList.setOnClickListener {

                if (wishList.isSelected) {
                    wishList.setSelected(false)

                } else {
                    wishList.setSelected(true)
                }

            }

        }
    }
}


