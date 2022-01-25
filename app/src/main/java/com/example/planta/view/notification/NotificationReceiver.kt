package com.example.planta.view.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val serviceIntent = Intent(context,NotificationsService::class.java)
        context.startService(serviceIntent)
    }
}

