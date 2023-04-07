package com.example.asynctask

import android.database.DatabaseUtils
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.text.PrecomputedTextCompat.Params
import androidx.databinding.DataBindingUtil
import androidx.loader.content.AsyncTaskLoader
import com.example.asynctask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val countdownFragment = CountdownFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.progressIndicator.visibility = View.INVISIBLE

        binding.switch1.setOnCheckedChangeListener {
                buttonView,
                isChecked ->
            if (isChecked){
                supportFragmentManager.beginTransaction()
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .add(R.id.framleyout, countdownFragment)
                    .commit()
            }else {
                supportFragmentManager.beginTransaction()
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .remove(countdownFragment)
                    .commit()
            }


        }
    }

    fun uploadTask(view: View) {
        println("ONCLICK HERE")

    }



}