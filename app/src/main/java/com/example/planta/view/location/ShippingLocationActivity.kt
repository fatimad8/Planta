package com.example.planta.view.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.*
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.GridLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.planta.R
import com.example.planta.model.History
import com.example.planta.model.HistoryItem
import com.example.planta.util.SharedPreferencesHelper
import com.example.planta.view.home.cart.CartViewModel
import com.example.planta.view.home.profile.orderHistory.OrderHistoryViewModel
import java.time.LocalDate
import java.util.*

class ShippingLocationActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shipping_location)
        var shipRecyclerView=findViewById<RecyclerView>(R.id.shipRecyclerView)
        val vm: CartViewModel by viewModels()
        val vm2: OrderHistoryViewModel by viewModels()
        var buttonCountinue=findViewById<Button>(R.id.buttonCountinue)
        shipRecyclerView.layoutManager=GridLayoutManager(this,1)
        var uid=SharedPreferencesHelper.getUserId(this)
        var oid=SharedPreferencesHelper.getOrderId(this)
        var buttonAddAddress=findViewById<Button>(R.id.buttonAddAddress)


        buttonAddAddress.setOnClickListener {
            checkedPermision()
        }





       // shipRecyclerView.adapter=AddressAdapter()

        var totalPrice=intent.getIntExtra("totalPrice",0)
        buttonCountinue.setOnClickListener {
            vm2.createOrderHistory(uid, History(LocalDate.now().toString(), "", totalPrice, uid))
                .observeForever { newHistory ->
                    if (it != null) {
                        Toast.makeText(this, "Added Successfully", Toast.LENGTH_SHORT).show()
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
                                            item.price ,
                                            item.quantity
                                        )
                                    )
                                        .observeForever{
                                            if(it){
                                                vm.deleteUserCart(uid,oid,item.id).observeForever {

                                                 }
                                            }
                                        }

                                }
                            }
                        }
                    }


                }







        }



    }


    fun checkedPermision(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION),1)

        }else{

            showLocation()
        }
    }

    @SuppressLint("MissingPermission")
    fun showLocation(){
        var locationManger=getSystemService(LOCATION_SERVICE)as? LocationManager

        locationManger?.
        requestLocationUpdates(
            LocationManager.GPS_PROVIDER,0,0f,

            object : LocationListener {
                override fun onLocationChanged(location: Location) {


                    Thread(){
                        val geocoder: Geocoder
                        val addresses: List<Address>

                        geocoder = Geocoder(this@ShippingLocationActivity, Locale.getDefault())

                        addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1) // Here 1 represent max location result to returned, by documents it recommended 1 to 5


                        val address: String = addresses[0].getAddressLine(0) // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()

                        val city: String = addresses[0]?.locality
                        val state: String = addresses[0]?.getAdminArea()
                        val country: String = addresses[0]?.getCountryName()
                        val postalCode: String = addresses[0]?.getPostalCode()
                        val knownName: String = addresses[0]?.getFeatureName()
                        val uid=SharedPreferencesHelper.getUserId(this@ShippingLocationActivity)

                        var add=com.example.planta.model.Address(city,country,state,"",postalCode,uid)





                        runOnUiThread {
                            AddressViewModel().saveUserAddress(uid,add).observeForever {
                                if(it)
                                    Toast.makeText(this@ShippingLocationActivity, "Address Saved", Toast.LENGTH_SHORT).show()}
                        }
                    }.start()


                    //"${location.latitude}+${location.longitude}"
                }

            })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
            showLocation()
        }else{
            AlertDialog.Builder(this).apply {
                title="warning"
                setMessage("To access location go to setting-> allow location service")
                setPositiveButton("Ok",{dialog, which->

                })
            }.show()
        }
    }



}