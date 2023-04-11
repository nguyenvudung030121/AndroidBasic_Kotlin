package com.example.mvp_mediaapplication.login

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.mvp_mediaapplication.main.MainActivity
import com.example.mvp_mediaapplication.R
import com.example.mvp_mediaapplication.dataSource.User
import com.example.mvp_mediaapplication.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(), LoginView {
    private lateinit var binding: ActivityLoginBinding
    lateinit var loginPresenter: LoginPresenter
    var listOfUser: MutableList<User> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.progressBar.visibility = View.INVISIBLE

        //Add user to list
        getUserData()

        loginPresenter = LoginPresenter(this, listOfUser)

        autoLogin()

    }

    private fun autoLogin(){
        binding.edtEmail.setText("vudung@gmail.com")
        binding.edtPassword.setText("1")
        binding.btnLogin.performClick()
    }

    fun onFacebook(view: View){
        var intent = Intent(Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com/"))
        startActivity(intent)
    }

    fun onGoogle(view: View){
        var intent = Intent(Intent.ACTION_VIEW,Uri.parse("https://mail.google.com/mail/u/0/#inbox"))
        startActivity(intent)
    }


    fun onLogin(view: View) {
        var username = binding.edtEmail.text.toString().trim()
        var password = binding.edtPassword.text.toString().trim()

        if (username != "" && password != "") {
            loginPresenter.login(username, password)
        } else {
            Toast.makeText(this, "Username Or Password is Null !!!", Toast.LENGTH_SHORT).show()
        }

/*        //Close keyboard
        val imm: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)*/
    }

    override fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

    override fun showProgressbar() {
        binding.progressBar.visibility = View.VISIBLE
        binding.progressBar.isIndeterminate = false
        binding.progressBar.max = 1
        binding.progressBar.progress = 1

    }

    override fun loginCompleted() {
        Toast.makeText(this, "Login Successful", Toast.LENGTH_LONG).show()
    }

    override fun loginFailed() {
        Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
    }

    override fun goHomePage(username:String) {

        val user:User = listOfUser.first { user -> user.email == username }

        var intent = Intent(this, MainActivity::class.java)
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
        listOfUser.add(User("1", "1", "Nguyen Vu Dung", "03/01/2001", "Da Nang"))

    }
}