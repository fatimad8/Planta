package com.example.planta.view.home.mainScreen

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.cardview.widget.CardView
import com.example.planta.R
import com.example.planta.util.LocalizationUtil
import com.example.planta.util.SharedPreferencesHelper
import com.example.planta.view.notification.Notification
import com.example.planta.view.notification.NotificationReceiver
import com.example.planta.view.notification.NotificationsService
import com.example.planta.view.product.AccessoriesActivity
import com.example.planta.view.product.IndoorActivity
import com.example.planta.view.product.OutdoorActivity


class HomeFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_home, container, false)


            val serviceIntent = Intent(context, NotificationsService::class.java)
            context?.startService(serviceIntent)
//           setNotification()



        var indoor = v.findViewById<CardView>(R.id.indoorCardView)
        var outdoor = v.findViewById<CardView>(R.id.outdoorCardView)
        var accessories = v.findViewById<CardView>(R.id.accesoriesCardView)


        indoor.setOnClickListener {
            startActivity(Intent(context, IndoorActivity::class.java))
        }


        outdoor.setOnClickListener {
            startActivity(Intent(context, OutdoorActivity::class.java))
        }

        accessories.setOnClickListener {
            startActivity(Intent(context, AccessoriesActivity::class.java))
        }

        return v
    }


    private fun setNotification() {
        val notifyIntent = Intent(this.requireContext(), NotificationReceiver::class.java)
        val pendingIntent =
            PendingIntent.getBroadcast(context, 0, notifyIntent, PendingIntent.FLAG_CANCEL_CURRENT)
        val alarmUp = PendingIntent.getBroadcast(
            context,
            0,
            notifyIntent,
            PendingIntent.FLAG_NO_CREATE
        ) != null
        if (alarmUp) {
            val alarmManger =
                context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManger.setRepeating(
                AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis(),
                (1000 * 5).toLong(),
                pendingIntent
            )
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.profile_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.language -> {

                if(SharedPreferencesHelper.getLanguage(this.requireContext())=="en"){
                    LocalizationUtil.changeLanguage(context as Activity,"ar")
                    SharedPreferencesHelper.saveLanguage(context as Activity,"ar")
                }

                else{
                    LocalizationUtil.changeLanguage(context as Activity,"en")
                    SharedPreferencesHelper.saveLanguage(context as Activity,"en")

                }

            }
        }
        return super.onOptionsItemSelected(item)

    }



}