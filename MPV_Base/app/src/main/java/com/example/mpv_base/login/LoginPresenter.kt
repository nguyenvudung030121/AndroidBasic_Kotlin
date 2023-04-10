package com.example.mpv_base.login

import android.os.Handler
import android.os.Looper
import com.example.mpv_base.dataSource.User
import kotlin.concurrent.thread

class LoginPresenter(var loginView: LoginView, var listOfUser: MutableList<User>) {


    fun login(username: String, password: String) {

     /*   Thread(Runnable {
            kotlin.run {

                Looper.prepare()
                var handler = Handler(Looper.myLooper()!!)
                handler.post(Runnable {
                    kotlin.run {
                        loginView.showProgressbar()
                    }
                })
                Looper.loop()

                Thread.sleep(4000)

                if (validUser(username, password)) {
                    loginView.hideProgressBar()
                    loginView.loginCompleted()
                    loginView.goHomePage()
                } else {
                    loginView.hideProgressBar()
                    loginView.loginFailed()
                    loginView.clearInput()
                }
            }
        }).start()*/

        loginView.clearInput()
        loginView.hideProgressBar()

        if (validUser(username, password)) {
            loginView.loginCompleted()
            loginView.goHomePage(username)
        } else {
            loginView.loginFailed()
        }



    }


    fun validUser(username: String, password: String): Boolean {

        if (listOfUser.any {
                it.email == username && it.password == password
            }) {
            return true
        }
        return false
    }

}