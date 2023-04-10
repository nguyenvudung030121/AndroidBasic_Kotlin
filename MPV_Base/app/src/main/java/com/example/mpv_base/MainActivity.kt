package com.example.mpv_base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.mpv_base.dataSource.User
import com.example.mpv_base.databinding.ActivityMainBinding


/*

1. Tạo dataSource - class User
2. Tạo một Interface View - LoginView() - chứa những gì view cần xử lí
3. Tạo một Presenter - để xử lý logic của view và data
4. Vào activity implement Interface - Override lại pt của interface
5. Khai báo presenter trong activity để Xử lí sự kiện

 */

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        val user:User = intent.getParcelableExtra("data")!!
        binding.txtHomepage.text = "Hello +${user.name}+ "

    }
}