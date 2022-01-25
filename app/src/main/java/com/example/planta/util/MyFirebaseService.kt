package com.example.planta.util

import android.app.Service
import android.content.ContentValues.TAG
import android.content.Intent
import android.nfc.Tag
import android.os.IBinder
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d(TAG,"From: ${remoteMessage.from}")
        remoteMessage.notification?.let {
            Log.d(TAG,"Message Notification Body: ${it.body}")
        }
     }
 }