package com.example.notify_services_example

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.databinding.DataBindingUtil
import com.example.notify_services_example.broadcast.Broadcast_RestartService
import com.example.notify_services_example.databinding.ActivityMainBinding
import com.example.notify_services_example.service.MessageService
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    companion object {
        val CHANNEL_ID = "CHANNEL_1"
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.btnSendNotify.setOnClickListener {
            createNotificationChannel()
            sendNotification()
        }

        binding.btnStartServices.setOnClickListener {
            clickStartServices()
        }

        binding.btnStopServices.setOnClickListener {
            clickStopServices()
        }

        binding.btnCloseApp.setOnClickListener {
            finish()
        }

    }

    override fun onDestroy() {

        val broadcastIntent = Intent(this,MessageService::class.java)
        broadcastIntent.action = "restartservice"
        broadcastIntent.setClass(this, Broadcast_RestartService::class.java)
        broadcastIntent.putExtra("Message", binding.edtMessage.text.toString().trim())
        this.sendBroadcast(broadcastIntent)

        super.onDestroy()
    }

    private fun clickStopServices() {
        val intent = Intent(this, MessageService::class.java)
        stopService(intent)
    }

    private fun clickStartServices() {
        val intent = Intent(this, MessageService::class.java)
        intent.putExtra("Message", binding.edtMessage.text.toString().trim())

        startService(intent)

    }

    private fun sendNotification() {

        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent =
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)


        var builder =
            NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("My notification")
                .setContentText("Hello World !")
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

        /* with(NotificationManagerCompat.from(this)) {
             // notificationId is a unique int for each notification that you must define
             notify(getNotificationId(), builder.build())
         }
 */
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

    private fun getNotificationId(): Int = Date().seconds
}