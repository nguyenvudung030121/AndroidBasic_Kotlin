package com.example.mvp_mediaapplication.login

interface LoginView {

    fun hideProgressBar()
    fun showProgressbar()
    fun loginCompleted()
    fun loginFailed()
    fun goHomePage(username:String)
    fun clearInput()

}