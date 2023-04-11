package com.example.mvp_mediaapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.mvp_mediaapplication.dataSource.User
import com.example.mvp_mediaapplication.databinding.ActivityUserProfileBinding

class UserProfile_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityUserProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_user_profile)

        val user: User = intent.getParcelableExtra("data")!!
        binding.activity = user

    }

    fun onBackClick(view: View) {
        this.finish()
    }

    fun onHomePage(view: View) {
        val user:User = intent.getParcelableExtra("data")!!
        var intent = Intent(this,MainActivity::class.java)
        intent.putExtra("data",user)
        startActivity(intent)
    }


}