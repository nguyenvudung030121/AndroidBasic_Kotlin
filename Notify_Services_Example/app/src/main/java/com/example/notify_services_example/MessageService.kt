package com.example.notify_services_example

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.notify_services_example.MainActivity.Companion.CHANNEL_ID
import kotlin.math.log


class MessageService : Service() {


    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        Log.e("VuDung","Create Service")
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e("VuDung","Start Service")

        val intent_message = intent!!.getStringExtra("Message").toString()
        sendNotify(intent_message)
        return START_NOT_STICKY
    }

    private fun sendNotify(message:String) {

        val intent = Intent(this,MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent:PendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_IMMUTABLE)


        var builder = NotificationCompat.Builder(this,CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("My Notification")
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .build()

        startForeground(1,builder)
    }

    override fun onDestroy() {
        Log.e("VuDung","Destroy Service")
        super.onDestroy()
    }
}