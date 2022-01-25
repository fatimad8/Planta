package com.example.planta.view.details

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.planta.R
import com.example.planta.model.*
import com.example.planta.util.SharedPreferencesHelper
import com.example.planta.view.home.cart.CartViewModel
import com.example.planta.view.home.profile.wishList.WishListViewModel
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
        val vm2: WishListViewModel by viewModels()


        val auth = Firebase.auth


        val wishList = findViewById<SmallBangView>(R.id.like_heart)
        val close = findViewById<ImageView>(R.id.imageViewClose)
        val productPhoto = findViewById<ImageView>(R.id.imageViewDetailsPhoto)
        val prodductName = findViewById<TextView>(R.id.textViewDetName)
        val prodductPrice = findViewById<TextView>(R.id.textViewDetPrice)
        val prodductDes = findViewById<TextView>(R.id.textViewDetDesc)
        val productStock = findViewById<TextView>(R.id.inStockTvDec)
        val spinner = findViewById<Spinner>(R.id.spinner)
        val addbtn = findViewById<Button>(R.id.buttonAddCart)
        val shareIcon=findViewById<ImageView>(R.id.imageViewShare)

        val id = SharedPreferencesHelper.getUserId(this)
        val wid= SharedPreferencesHelper.getWishListId(this)
        val uid = auth.currentUser?.uid
        var total_price = 0
        var totalOrderPrice = 0
        var oId = ""
        val currentdate = LocalDate.now().toString()
        var order = Order("", "", 0, "", "")


        val product = intent.getSerializableExtra("product") as Product
        //var product_item = intent.getSerializableExtra("item") as Item


        Picasso.get().load(product.photo).into(productPhoto)
        prodductName.text = product.name
        prodductPrice.text = product.price.toString()
        prodductDes.text = product.description
        var pId = product.id
        val price = product.price
        val date = LocalDate.now()
        var item: Any = 0


        if (product.inStock == true) {
            productStock.text = getString(R.string.in_stock)
            productStock.setTextColor(Color.parseColor("#6D953F"))
        } else {
            productStock.text = getString(R.string.out_of_stock)
            productStock.setTextColor(Color.parseColor("#DF3D31"))
            addbtn.isEnabled = false

        }


        shareIcon.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "${product.name}\n${product.price}\n${product.description}")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)

        }


        WishListViewModel().getUserWishlist(id,wid).observeForever {
            if(it!=null){
                for(p in it){
                    if(p.name==product.name){
                        wishList.isSelected=true
                    }
                }
            }

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
            if(SharedPreferencesHelper.getUserId(this).equals("null")){
                SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Oops...")
                    .setContentText("Please Login to add to cart")
                    .show()}
            if (SharedPreferencesHelper.getOrderId(this) == "null") {
                var order=Order("",date.toString(),item as Int,price,id)
                vm.createNewOrder(id,order).observeForever {
                    if (it != null) {
                        SharedPreferencesHelper.saveOrderId(this, it.id)
                        //Toast.makeText(this, getString(R.string.new_order), Toast.LENGTH_SHORT).show()
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
                                (item as Int)
                             )
                        ).observeForever {
                            if (it)
                                Toast.makeText(this, getString(R.string.item_added), Toast.LENGTH_SHORT)
                                    .show()
                        }

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
                        (item as Int)
                     )
                ).observeForever {
                    if (it)
                        Toast.makeText(this, getString(R.string.item_added), Toast.LENGTH_SHORT)
                            .show()
                }
            }
        }


        wishList.setOnClickListener {

            if(SharedPreferencesHelper.getUserId(this).equals("null")){
                SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Oops...")
                    .setContentText("Please Login to add to wishlist")
                    .show()}

            if (wishList.isSelected) {
                wishList.setSelected(false)
                vm2.getLidByName(id,wid,product.name).observe(this,{
                    var lid=it[0].id
                    vm2.removeFromWishlist(id,wid,lid).observe(this,{
                        wishList.isSelected=!it
                    })
                })


            } else {
                if (SharedPreferencesHelper.getWishListId(this) == "null") {
                    vm2.WishList(id, WishList("", id)).observeForever {
                        if (it != null) {
                            SharedPreferencesHelper.saveWishId(this, it.id)
                            vm2.addLikedItem(
                                id,
                                it.id,
                                Liked(
                                    product.category,
                                    product.description,
                                    "",
                                    product.name,
                                    product.photo,
                                    product.price,
                                    product.quantity,
                                    it.id
                                )
                            )
                                .observeForever {
                                    if (it) {
                                        wishList.setSelected(true)

                                    } else {
                                        wishList.setSelected(false)
                                    }

                                }
                        }
                    }
                } else {
                    var lasWishId = SharedPreferencesHelper.getWishListId(this)
                    vm2.addLikedItem(
                        id, lasWishId, Liked(
                            product.category,
                            product.description,
                            "",
                            product.name,
                            product.photo,
                            product.price,
                            product.quantity,
                            lasWishId
                        )
                    )
                }


                wishList.setSelected(true)
            }

        }
    }
}


