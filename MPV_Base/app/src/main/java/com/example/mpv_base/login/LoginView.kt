package com.example.mpv_base.login

interface LoginView {

    fun hideProgressBar()
    fun showProgressbar()
    fun loginCompleted()
    fun loginFailed()
    fun goHomePage(username:String)
    fun clearInput()

}