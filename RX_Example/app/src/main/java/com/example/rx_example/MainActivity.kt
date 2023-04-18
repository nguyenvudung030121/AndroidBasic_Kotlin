package com.example.rx_example

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rx_example.ui.login.LoginActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers


/*
    - Khai báo 1 CompositeDisposable.Clear() để hủy Observer khi không còn muốn lắng nghe từ Observable nữa - đặt nó ở Destroy
    - Add đăng ký observer vào disposable bằng disposable.add
    - Tạo 1 Hàm Observable để phát ra danh sách dữ liệu
    - Tạo 1 Hàm Observer để nhận dữ liệu
    - Dùng hàm Observable để đăng ký Observer
    - Có thể sử dụng JavaxKotlin sau đó dùng to Observable để biến list thành observable
    - Dùng Operator (toán tử) - đối tượng dùng để biến đổi dữ liệu phát ra
*/

class MainActivity : AppCompatActivity() {

    private var disposable = CompositeDisposable()
    private val TAG: String = MainActivity::class.java.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.button)
        val button2: Button = findViewById(R.id.button_2)
        val button3: Button = findViewById(R.id.btn_act_2)

        button.setOnClickListener {

/*     - subcribeOn(Schedulers.io()): Điều này bảo Observable chạy task tron background thread
       - observeOn(AndroidSchedulers.mainThread()): Điều này báo cho Observer
      nhận data ở Android Main Thread để bạn có thể thực hiện các hành động liên quan đến UI.
*/

            disposable.add(footballPlayerObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter{it.startsWith("M")}
                .subscribe({ result: String -> println("Result: $result") },  // xử lý kết quả thành công
                    { error: Throwable -> println("Error: " + error.message) })
            )

            disposable.add(footballPlayerObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter{it.startsWith("P")}
                .subscribe({ result: String -> println("Result: $result") },  // xử lý kết quả thành công
                    { error: Throwable -> println("Error: " + error.message) })
            )



        }



        button2.setOnClickListener {
            val list = listOf("1", "2", "3", "4", "5")

            disposable.add(Observable.fromIterable(list)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result: String -> println("Result: $result") },  // xử lý kết quả thành công
                    { error: Throwable -> println("Error: " + error.message) })
            )

        }

        button3.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }


    }

    fun mfootballPlayerObserver(): Observer<String> {
        return object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, "m - onSubscribe")
            }

            override fun onError(e: Throwable) {
                Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_LONG).show()
            }

            override fun onComplete() {
                Log.d(TAG, "m - onComplete")
            }

            override fun onNext(t: String) {
                Log.d(TAG, t)
            }

        }

    }

    fun footballPlayerObservable(): Observable<String> {
        return Observable.just("Penaldo","Messi","Maradona","Pele")
    }

    override fun onDestroy() {
        // Using clear will clear all, but can accept new disposable
        disposable.clear();
        // Using dispose will clear all and set isDisposed = true, so it will not accept any new disposable
        disposable.dispose();
        super.onDestroy()
    }
}




