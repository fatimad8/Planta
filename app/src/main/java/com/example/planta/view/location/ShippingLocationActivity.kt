package com.example.planta.view.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.*
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.GridLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.planta.R
import com.example.planta.model.History
import com.example.planta.model.HistoryItem
import com.example.planta.util.SharedPreferencesHelper
import com.example.planta.view.home.cart.CartViewModel
import com.example.planta.view.home.cart.OrderSummaryActivity
import com.example.planta.view.home.mainScreen.MainActivity
import com.example.planta.view.home.profile.orderHistory.OrderHistoryViewModel
import com.example.planta.view.notification.Notification
import com.google.android.material.bottomsheet.BottomSheetDialog
import okhttp3.internal.notifyAll
import java.time.LocalDate
import java.util.*

private const val TAG = "ShippingLocationActivit"

class ShippingLocationActivity : AppCompatActivity() {
    lateinit var countryEdit: EditText
    lateinit var cityEdit: EditText
    lateinit var stateEdit: EditText
    lateinit var codeEdit: EditText
    lateinit var add: com.example.planta.model.Address
    lateinit var addre: String
    var addList = mutableListOf<com.example.planta.model.Address>()


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shipping_location)

        var shipRecyclerView = findViewById<RecyclerView>(R.id.shipRecyclerView)
        var buttonCountinue = findViewById<Button>(R.id.buttonCountinue)
        var buttonAddAddress = findViewById<Button>(R.id.buttonAddAddress)
        var aToolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.aToolbar)

        SharedPreferencesHelper.saveAddress(this, "null")

        var address: String = " "


        var uid = SharedPreferencesHelper.getUserId(this)
        var oid = SharedPreferencesHelper.getOrderId(this)

        val vm: CartViewModel by viewModels()
        val vm2: OrderHistoryViewModel by viewModels()
        val vm3: AddressViewModel by viewModels()

        aToolbar.title = getString(R.string.myAddress)
        aToolbar.setTitleTextColor(Color.WHITE)

        var mAdapter = AddressAdapter(addList)
        shipRecyclerView.adapter = mAdapter

        setSupportActionBar(aToolbar)
        aToolbar.setNavigationOnClickListener {
            finish()
        }



        shipRecyclerView.layoutManager = GridLayoutManager(this, 1)

        vm3.userAdresses.observe(this, {
            Log.d(TAG, "Observed")

            addList.clear()
            addList.addAll(it)
            mAdapter.notifyDataSetChanged()
            address = mAdapter.addr
        })
        vm3.getUserAddress(uid)






        buttonAddAddress.setOnClickListener {
            checkedPermision()
            var bottomSheet = BottomSheetDialog(this)
            bottomSheet.setContentView(R.layout.confirm_address_dialog)
            bottomSheet.setCanceledOnTouchOutside(false)
            var save = bottomSheet.findViewById<Button>(R.id.buttonSaveAddress)
            countryEdit = bottomSheet.findViewById(R.id.counryEditText)!!
            cityEdit = bottomSheet.findViewById(R.id.cityEditText)!!
            stateEdit = bottomSheet.findViewById(R.id.stateEditText)!!
            codeEdit = bottomSheet.findViewById(R.id.codeEditText)!!



            save?.setOnClickListener {
                var userAddress = com.example.planta.model.Address(
                    cityEdit.text.toString(),
                    countryEdit.text.toString(),
                    stateEdit.text.toString(),
                    "",
                    codeEdit.text.toString(),
                    uid
                )
                AddressViewModel().saveUserAddress(uid, userAddress).observeForever {
                    if (it) {
                        bottomSheet.dismiss()
                        vm3.getUserAddress(uid)

                    }


                }

            }
            bottomSheet.show()

        }


        var totalPrice = intent.getIntExtra("totalPrice", 0)

        buttonCountinue.setOnClickListener {

            if (SharedPreferencesHelper.getAddress(this) == "null") {
                SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText(getString(R.string.Oops))
                    .setContentText(getString(R.string.Please_select_address))
                    .show()
            } else {
                var i = Intent(this, OrderSummaryActivity::class.java)
                i.putExtra("totalPrice", totalPrice)
                i.putExtra("address", address)
                startActivity(i)
            }
        }

    }

    fun checkedPermision() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ), 1
            )

        } else {

            showLocation()
        }
    }

    @SuppressLint("MissingPermission")
    fun showLocation() {
        var locationManger = getSystemService(LOCATION_SERVICE) as? LocationManager


        locationManger?.requestLocationUpdates(
            LocationManager.GPS_PROVIDER, 0, 0f,

            object : LocationListener {
                override fun onLocationChanged(location: Location) {

                    Thread() {
                        val geocoder: Geocoder
                        val addresses: List<Address>

                        geocoder = Geocoder(this@ShippingLocationActivity, Locale.getDefault())

                        addresses = geocoder.getFromLocation(
                            location.latitude,
                            location.longitude,
                            1
                        ) // Here 1 represent max location result to returned, by documents it recommended 1 to 5

                        val address: String =
                            addresses[0].getAddressLine(0) // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()

                        val city: String = addresses[0]?.locality
                        val state: String = addresses[0]?.getAdminArea()
                        val country: String = addresses[0]?.getCountryName()
                        val postalCode: String = addresses[0]?.getPostalCode()
                        val knownName: String = addresses[0]?.getFeatureName()
                        val uid = SharedPreferencesHelper.getUserId(this@ShippingLocationActivity)

                        add = com.example.planta.model.Address(
                            city,
                            country,
                            state,
                            "",
                            postalCode,
                            uid
                        )

                        runOnUiThread {
                            countryEdit.text = Editable.Factory.getInstance().newEditable(country)
                            stateEdit.text = Editable.Factory.getInstance().newEditable(state)
                            cityEdit.text = Editable.Factory.getInstance().newEditable(city)
                            codeEdit.text = Editable.Factory.getInstance().newEditable(postalCode)
                            add = com.example.planta.model.Address(
                                cityEdit.text.toString(),
                                countryEdit.text.toString(),
                                stateEdit.text.toString(),
                                "",
                                codeEdit.text.toString(),
                                uid
                            )
                            locationManger.removeUpdates(this)
                        }

                    }.start()

                }

            })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            showLocation()
        } else {
            AlertDialog.Builder(this).apply {
                title = getString(R.string.warning)
                setMessage(getString(R.string.access_location))
                setPositiveButton(getString(R.string.ok)) { dialog, which ->

                }
            }.show()
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "OnResume is called")
    }


}