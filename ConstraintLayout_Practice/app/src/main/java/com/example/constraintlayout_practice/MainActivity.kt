package com.example.constraintlayout_practice

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.core.os.bundleOf
import com.example.constraintlayout_practice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root

        setContentView(view)

//        binding.btnLogin.setOnClickListener(object :View.OnClickListener{
//            override fun onClick(v: View?) {
//
//            }
//        })

        binding.btnLogin.setOnClickListener(View.OnClickListener {


            var email = binding.edtEmail.text.toString()
            var password = binding.edtPassword.text.toString()
            var name = "Nguyen Vu Dung"
            var dob = "03/01/2001"
            var addr = "Dong Da - Hai Chau - Da Nang"

            if (email.equals("") || password.equals("")) {
                Toast.makeText(this, "Email or Password is Null!!", Toast.LENGTH_SHORT).show()
            } else {
                val user = User(email, password, name, dob, addr)

//            val bundle = Bundle()
//
//            bundle.putParcelable("user",user)

                intent = Intent(this, UserProfile::class.java)

//            intent.putExtra("data",bundle)

                intent.putExtra("user", user)

                startActivity(intent)
            }
        })

        binding.btnFacebook.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intentOpenFacebook =
                    Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/"))
                startActivity(intentOpenFacebook)
            }

        })

        binding.btnGoogle.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intentOpenGoogleAccount =
                    Intent(Intent.ACTION_VIEW, Uri.parse("https://myaccount.google.com/"))
                startActivity(intentOpenGoogleAccount)

            }

        })


    }
}