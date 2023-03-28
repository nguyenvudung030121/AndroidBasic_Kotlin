package com.example.constraintlayout_practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.constraintlayout_practice.databinding.ActivityUserProfileBinding

class UserProfile : AppCompatActivity() {
    private lateinit var binding: ActivityUserProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityUserProfileBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        // Sử dụng Bundle
        /* val bundle = intent.getBundleExtra("data")

        val userProfile:User = bundle?.getParcelable("user")!!
        binding.userModel = userProfile */

        // Sử dụng Intent

        val userProfile:User = intent.getParcelableExtra("user")!!

        binding.userModel = userProfile

        binding.btnBack.setOnClickListener{
            finish()
        }

    }
}