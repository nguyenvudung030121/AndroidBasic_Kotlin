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
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.progressIndicator.visibility = View.INVISIBLE

    }

    fun uploadTask(view: View){
        println("ONCLICK HERE")
        UploadTask().execute()
    }

    inner class UploadTask:AsyncTask<String,Int,String>() {
        override fun doInBackground(vararg params: String?): String {
            Log.d("Vd","Running")
            for (i in 0..9){
                Thread.sleep(1000)
                publishProgress(i);
            }


            return "Task completed"
        }

        override fun onPreExecute() {
            super.onPreExecute()
            binding.textView.text = "Uploading data"
            binding.progressIndicator.visibility = View.VISIBLE
            binding.button.isEnabled = false
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            binding.textView.text = result
            binding.progressIndicator.visibility = View.INVISIBLE
            binding.button.isEnabled = true

        }

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            binding.progressBar.progress = values[0]!!+1
        }
    }


}