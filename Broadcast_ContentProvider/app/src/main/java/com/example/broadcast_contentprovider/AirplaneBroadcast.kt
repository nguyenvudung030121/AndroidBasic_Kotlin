package com.example.broadcast_contentprovider

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AirplaneBroadcast: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        val isPlaneModeTurnOn:Boolean = intent!!.getBooleanExtra("state",false)

        if (isPlaneModeTurnOn){
            Toast.makeText(context,"Airplane mode is turn on !",Toast.LENGTH_LONG).show()
        }else {
            Toast.makeText(context,"Airplane mode is turn off !",Toast.LENGTH_LONG).show()
        }

    }
}