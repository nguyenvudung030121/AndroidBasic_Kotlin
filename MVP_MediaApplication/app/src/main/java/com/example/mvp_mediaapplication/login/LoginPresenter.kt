package com.example.mvp_mediaapplication.login

import android.os.Handler
import android.os.Looper
import com.example.mvp_mediaapplication.dataSource.User

class LoginPresenter(var loginView: LoginView, var listOfUser: MutableList<User>) {


    fun login(username: String, password: String) {
        loginView.showProgressbar()


        var handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            loginView.hideProgressBar()
            loginView.clearInput()

            if (validUser(username, password)) {
                loginView.loginCompleted()
                loginView.goHomePage(username)
            } else {
                loginView.loginFailed()
            }
        }, 2000L)

    }
/*        CACH 2
        Thread(Runnable {
            kotlin.run {

                Looper.prepare()
                var handler = Handler(Looper.getMainLooper())
                handler.post(Runnable {
                    kotlin.run {
                        loginView.showProgressbar()
                    }
                })

                Thread.sleep(2000)


                handler.post(Runnable {
                    loginView.hideProgressBar()
                    loginView.clearInput()

                    if (validUser(username, password)) {
                        loginView.loginCompleted()
                        loginView.goHomePage(username)
                    } else {
                        loginView.loginFailed()
                    }
                })


                Looper.loop()
            }
        }).start()

    }*/


    fun validUser(username: String, password: String): Boolean {

        if (listOfUser.any {
                it.email == username && it.password == password
            }) {
            return true
        }
        return false
    }

}