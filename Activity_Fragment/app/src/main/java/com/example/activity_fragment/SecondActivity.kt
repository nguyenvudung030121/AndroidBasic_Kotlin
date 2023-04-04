package com.example.activity_fragment

import android.app.Dialog
import android.database.DatabaseUtils
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import com.example.activity_fragment.databinding.ActivitySecondBinding
import com.example.activity_fragment.fragment.FirstFragment

class SecondActivity : AppCompatActivity() {

    lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_second)

        var message = intent.getStringExtra("Message")

        binding.txtMessage.text = message

        binding.btnSendToFragment.setOnClickListener {
            if (savedInstanceState == null){
                supportFragmentManager.beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.framelayout_1,FirstFragment::class.java,null)
                    .commit()
            }

        }

    }

    override fun onStart() {
        Log.d("VDung", "Activity 2 - OnStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("VDung", "Activity 2 - OnResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("VDung", "Activity 2 - OnPause")
        Log.e("VDung", "")

        super.onPause()
    }

    override fun onStop() {
        Log.d("VDung", "Activity 2 - OnStop")
        Log.e("VDung", "")
        super.onStop()
    }

    override fun onRestart() {
        Log.d("VDung", "Activity 2 - OnRestart")
        super.onRestart()
    }

    override fun onDestroy() {
        Log.d("VDung", "Activity 2 - OnDestroy")
        super.onDestroy()
    }

}