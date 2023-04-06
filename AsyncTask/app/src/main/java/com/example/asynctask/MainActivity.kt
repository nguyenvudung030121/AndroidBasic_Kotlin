package com.example.asynctask

import android.database.DatabaseUtils
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.loader.content.AsyncTaskLoader
import com.example.asynctask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.progressIndicator.visibility = View.INVISIBLE

    }

    fun uploadTask(view: View){

    }

    class UploadTask():AsyncTaskLoader()
}