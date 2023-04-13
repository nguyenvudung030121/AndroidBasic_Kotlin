package com.example.rx_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable


/*
    - Khai báo 1 CompositeDisposable để hủy Observeble khi activity bị destroy
    - Tạo 1 Hàm Observable để phát ra danh sách dữ liệu
    - Tạo 1 Hàm Observer để nhận dữ liệu
    - Dùng hàm Observable để đăng ký Observer
    - Có thể sử dụng JavaxKotlin sau đó dùng to Observable để biến list thành observable
*/

class MainActivity : AppCompatActivity() {

    private val disposable = CompositeDisposable()
    private val TAG: String = MainActivity::class.java.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.button)
        val button2: Button = findViewById(R.id.button_2)

        button.setOnClickListener {
            val footbalObservable = footballPlayerObservable()

            val footbalObserver = footballPlayerObserver()

            footbalObservable
                .subscribe(footbalObserver)

        }


        button2.setOnClickListener {
            val list = listOf("1", "2", "3", "4", "5")
//            val observable:Observable<String> = list.toObservable()
                
        }


    }

    fun footballPlayerObserver(): Observer<String> {
        return object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, "onSubscribe")
            }

            override fun onError(e: Throwable) {
                Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_LONG).show()
            }

            override fun onComplete() {
                Log.d(TAG, "onComplete")
            }

            override fun onNext(t: String) {
                Log.d(TAG, t)
            }

        }

    }

    fun footballPlayerObservable(): Observable<String> {
        return Observable.just("Ronaldo", "Messi", "Pele", "Maradona")
    }

    override fun onDestroy() {
        disposable.isDisposed
        super.onDestroy()
    }
}


