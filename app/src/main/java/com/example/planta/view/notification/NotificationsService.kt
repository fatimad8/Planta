package com.example.planta.view.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.planta.R
import com.example.planta.util.SharedPreferencesHelper
import com.example.planta.view.home.mainScreen.HomeFragment

class NotificationsService : Service() {
    private val CHANNEL_ID = "Notification from Service"

    override fun onCreate() {

            createNotificationChannel()
            showNotification()


    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }



    fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val CHANNEL_ID = "show_dialog"
            val channel = NotificationChannel(CHANNEL_ID, "name", importance).apply {
                description = "descriptionText"
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
    fun showNotification(){
        val intent = Intent(this, HomeFragment::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        var toBitMap = BitmapFactory.decodeResource(applicationContext.resources, R.mipmap.ic_launcher)
        val builder = NotificationCompat.Builder(this, "show_dialog")
            .setLargeIcon(toBitMap)
            .setSmallIcon(R.drawable.ic_baseline_notifications_24)
            .setContentTitle("Look Up!")
            .setContentText("Plants give us Oxygen for the lungs and soul.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            // Set the intent that will fire when the user taps the notification
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
        with(NotificationManagerCompat.from(this)) {
            notify(1, builder.build())
        }
    }


}