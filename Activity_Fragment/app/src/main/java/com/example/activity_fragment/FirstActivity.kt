package com.example.activity_fragment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.activity_fragment.databinding.ActivityFirstBinding

class FirstActivity : AppCompatActivity() {
    lateinit var binding: ActivityFirstBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("VDung-Activity","Activity 1 - OnCreate")
        binding = DataBindingUtil.setContentView(this, R.layout.activity_first)

        binding.btnSubmit.setOnClickListener {
            var message = binding.edtMessage.text.toString()
            if (message == "") {
                Toast.makeText(this, "Message is NULL !!!", Toast.LENGTH_SHORT).show()
            } else {
                intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("Message",message.trim())
                startActivity(intent)
            }
        }
    }

    override fun onStart() {
        Log.d("VDung-Activity", "Activity 1 - OnStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("VDung-Activity", "Activity 1 - OnResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("VDung-Activity", "Activity 1 - OnPause")
        Log.e("VDung-Activity", "")

        super.onPause()
    }

    override fun onStop() {
        Log.d("VDung-Activity", "Activity 1 - OnStop")
        Log.e("VDung-Activity", "")
        super.onStop()
    }

    override fun onRestart() {
        Log.d("VDung-Activity", "Activity 1 - OnRestart")
        super.onRestart()
    }

    override fun onDestroy() {
        Log.d("VDung-Activity", "Activity 1 - OnDestroy")
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d("VDung-Activity", "Activity 1 - onSaveInstanceState")
        super.onSaveInstanceState(outState)
    }

}