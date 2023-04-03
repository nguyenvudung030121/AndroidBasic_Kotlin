package com.example.broadcast_contentprovider

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class MainActivity : AppCompatActivity() {
    var airplaneBroadcast = AirplaneBroadcast()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = IntentFilter()
        intent.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        LocalBroadcastManager.getInstance(this).registerReceiver(airplaneBroadcast,intent)

    }

    override fun onStop() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(airplaneBroadcast)
        super.onStop()
    }
}