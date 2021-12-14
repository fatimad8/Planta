package com.example.planta.view.details

import android.graphics.Color
import android.media.Image
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.planta.R
import com.example.planta.model.Product
import com.example.planta.view.Home.cart.CartViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import xyz.hanks.library.bang.SmallBangView
import java.time.LocalDate
import java.util.*

class DetailsActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val vm: CartViewModel by viewModels()

        var auth = Firebase.auth


        var wishList = findViewById<SmallBangView>(R.id.like_heart)
        var close = findViewById<ImageView>(R.id.imageViewClose)
        var productPhoto = findViewById<ImageView>(R.id.imageViewDetailsPhoto)
        var prodductName = findViewById<TextView>(R.id.textViewDetName)
        var prodductPrice = findViewById<TextView>(R.id.textViewDetPrice)
        var prodductDes = findViewById<TextView>(R.id.textViewDetDesc)
        var productStock = findViewById<TextView>(R.id.textViewDetStock)
        var spinner = findViewById<Spinner>(R.id.spinner)
        var addbtn = findViewById<Button>(R.id.buttonAddCart)


        var product = intent.getSerializableExtra("product") as Product


        Picasso.get().load(product.photo).into(productPhoto)
        prodductName.text = product.name
        prodductPrice.text = product.price.toString()
        prodductDes.text = product.description
        var pId=product.id
        var price= product.price
        val date=LocalDate.now()


        if (product.inStock == true) {
            productStock.text = "In Stock"
            productStock.setTextColor(Color.parseColor("#6D953F"))
        } else {
            productStock.text = "Out of stock"
            productStock.setTextColor(Color.parseColor("#DF3D31"))
            addbtn.isEnabled=false

        }


        var quntList = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        spinner.adapter = ArrayAdapter<Int>(this, android.R.layout.simple_spinner_item, quntList)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                var item = parent.getItemAtPosition(pos).toString()

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        close.setOnClickListener {
            finish()
        }

        addbtn.setOnClickListener {

           var uid= auth.currentUser?.uid
            if (uid != null) {
                vm.addToCart(date.toString(),uid,price).observeForever {
                    if(it){
                        Toast.makeText(this, "added successfully", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }


        wishList.setOnClickListener {

            if (wishList.isSelected) {
                wishList.setSelected(false)

            } else {
                wishList.setSelected(true)
            }

        }
    }
}