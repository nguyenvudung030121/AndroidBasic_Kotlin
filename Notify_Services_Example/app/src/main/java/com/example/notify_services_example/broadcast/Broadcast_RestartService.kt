package com.example.notify_services_example.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.notify_services_example.service.MessageService

class Broadcast_RestartService:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
//        Toast.makeText(context, intent?.getStringExtra("Message"), Toast.LENGTH_SHORT).show()

        val newIntent = Intent(context,MessageService::class.java)
        newIntent.putExtra("Message",intent?.getStringExtra("Message"))

        context?.startService(newIntent)
    }
}