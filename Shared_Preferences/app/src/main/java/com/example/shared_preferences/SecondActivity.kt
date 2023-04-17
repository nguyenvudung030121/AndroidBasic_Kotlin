package com.example.shared_preferences

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.shared_preferences.databinding.ActivitySecondBinding
import com.google.gson.Gson

class SecondActivity : AppCompatActivity() {
    val gson = Gson()

    private lateinit var binding:ActivitySecondBinding
    private lateinit var getSharedPreferences:SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_second)
        getSharedPreferences = getSharedPreferences("login", MODE_PRIVATE)
    }

    override fun onResume() {
        super.onResume()
        getData()
        onLogout()
    }

    private fun getData(){

        var jsonString = getSharedPreferences.getString("user","")
        val user = gson.fromJson(jsonString,User::class.java)
        binding.user = user

    }

    private fun onLogout(){
        binding.logout.setOnClickListener {
            with(getSharedPreferences.edit()){
                clear()
                apply()
            }
            this.finish()
        }
    }
}