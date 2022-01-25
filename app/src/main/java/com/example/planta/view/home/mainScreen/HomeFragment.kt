package com.example.planta.view.home.mainScreen

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import com.example.planta.R
import com.example.planta.util.SharedPreferencesHelper
import com.example.planta.view.notification.Notification
import com.example.planta.view.notification.NotificationReceiver
import com.example.planta.view.notification.NotificationsService
import com.example.planta.view.product.AccessoriesActivity
import com.example.planta.view.product.IndoorActivity
import com.example.planta.view.product.OutdoorActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_home, container, false)

//        setNotification()
        var oid = SharedPreferencesHelper.getOrderId(requireContext())
        if (oid!="null"){
            val serviceIntent = Intent(context, NotificationsService::class.java)
            context?.startService(serviceIntent)
        }




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
                (1000 * 60).toLong(),
                pendingIntent
            )
        }
    }


}