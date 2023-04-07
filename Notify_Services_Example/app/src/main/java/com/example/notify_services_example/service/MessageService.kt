package com.example.notify_services_example.service

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.*
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.notify_services_example.MainActivity
import com.example.notify_services_example.MainActivity.Companion.CHANNEL_ID
import com.example.notify_services_example.R
import java.util.*


class MessageService : Service() {


    companion object {
        val RECEIVE_MESSAGE: Int = 1
    }

    //    lateinit var handler: Handler
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        Log.e("VuDung", "Create Service")
        createNotificationChannel()
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e("VuDung", "Start Service")

        val intent_message = intent?.getStringExtra("Message") ?: ""

//        Toast.makeText(this,intent_message, Toast.LENGTH_SHORT).show()
//        sendNotify(intent_message)

        thread(intent_message)
        return START_STICKY
    }

    fun thread(message: String) {
        Thread(Runnable {
            kotlin.run {
/*                var handlerMessage = Message()
                handlerMessage.what = RECEIVE_MESSAGE
                handlerMessage.obj = message
                handler.sendMessage(handlerMessage)*/
                Thread.sleep(3000)

                Looper.prepare()
                var handler = Handler(Looper.myLooper()!!)
                handler.post(Runnable {
                    kotlin.run {
                        sendNotify(message)
                        onDestroy()
                    }
                })
                Looper.loop()
            }
        }).start()

    }

    private fun sendNotify(message: String) {

        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent: PendingIntent =
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)


        var builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("My Notification")
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)


        val notificationManager = NotificationManagerCompat.from(this)
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }


        notificationManager.notify(getNotificationId(), builder.build())

/*//        Chạy ở foreGround -- Trạng thái này không thể xóa notify
        startForeground(getNotificationId(), builder.build())*/

    }


    override fun onDestroy() {
        Log.e("VuDung", "Destroy Service")
        Toast.makeText(this, "Destroy Service", Toast.LENGTH_SHORT).show()
        super.onDestroy()
    }

    fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun getNotificationId(): Int = Date().seconds
}