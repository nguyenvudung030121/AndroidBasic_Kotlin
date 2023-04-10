package com.example.mpv_base.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.mpv_base.R
import com.example.mpv_base.dataSource.User
import com.example.mpv_base.databinding.ActivityLoginBinding
import com.example.mpv_base.userProfile.UserProfile_Activity


class LoginActivity : AppCompatActivity(), LoginView {
    private lateinit var binding: ActivityLoginBinding
    lateinit var loginPresenter: LoginPresenter
    var listOfUser: MutableList<User> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.progressBar.visibility = View.INVISIBLE

        getUserData()

        loginPresenter = LoginPresenter(this, listOfUser)


    }

    fun onLogin(view: View) {
        var username = binding.edtEmail.text.toString().trim()
        var password = binding.edtPassword.text.toString().trim()

        if (username != "" && password != "") {
            loginPresenter.login(username, password)
        } else {
            Toast.makeText(this, "Username Or Password is Null !!!", Toast.LENGTH_SHORT).show()
        }

        }

    override fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

    override fun showProgressbar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun loginCompleted() {
        Toast.makeText(this, "Login Successful", Toast.LENGTH_LONG).show()
    }

    override fun loginFailed() {
        Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
    }

    override fun goHomePage(username:String) {

        val user:User = listOfUser.first { user -> user.email == username }

        var intent = Intent(this, UserProfile_Activity::class.java)
        intent.putExtra("data",user)

        startActivity(intent)
    }

    override fun clearInput() {
        binding.edtEmail.text = null
        binding.edtPassword.text = null
    }

    fun getUserData() {
        listOfUser.add(User("vudung@gmail.com", "1", "Nguyen Vu Dung", "03/01/2001", "Da Nang"))
        listOfUser.add(User("halan@gmail.com", "22", "Nguyen Vu Dung", "03/01/2001", "Da Nang"))
        listOfUser.add(User("ducan@gmail.com", "333", "Nguyen Vu Dung", "03/01/2001", "Da Nang"))
        listOfUser.add(
            User(
                "thanhtam@gmail.com",
                "4444",
                "Nguyen Vu Dung",
                "03/01/2001",
                "Da Nang"
            )
        )
        listOfUser.add(User("goku@gmail.com", "55555", "Nguyen Vu Dung", "03/01/2001", "Da Nang"))

    }
}