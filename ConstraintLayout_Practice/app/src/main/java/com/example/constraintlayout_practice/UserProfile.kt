package com.example.constraintlayout_practice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.constraintlayout_practice.databinding.ActivityUserProfileBinding

class UserProfile : AppCompatActivity() {
    private lateinit var binding: ActivityUserProfileBinding
//    val userP: MutableLiveData<User?> = MutableLiveData()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = DataBindingUtil.setContentView(this ,R.layout.activity_user_profile)
//        binding.setVariable(BR.activity, this)
//        binding.executePendingBindings()

        val view = binding.root
        setContentView(view)

        // Sử dụng Bundle
        /* val bundle = intent.getBundleExtra("data")

        val userProfile:User = bundle?.getParcelable("user")!!
        binding.userModel = userProfile */

        // Sử dụng Intent

        val userProfile:User = intent.getParcelableExtra("user")!!

        binding.activity = userProfile

//        userP.postValue(userProfile)

        binding.btnBack.setOnClickListener{
            finish()
        }

        binding.btnHomepage.setOnClickListener {
            val intent = Intent(this,ListsOfUsers::class.java)
            startActivity(intent)
        }

        //Quan sát bằng observe
//        userP.observe(this, Observer {
//            println("hihihi")
//        })

    }

//    fun onClick() {
//        println("Click")
//        userP.value = User("sadasd", "asdsd", "asdsd", "asdsd", "asdds")
//    }
}