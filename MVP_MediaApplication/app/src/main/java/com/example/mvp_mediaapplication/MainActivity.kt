package com.example.mvp_mediaapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.mvp_mediaapplication.dataSource.User
import com.example.mvp_mediaapplication.databinding.ActivityMainBinding


/*
 Ứng dụng nghe nhạc bao gồm:
    - Đăng nhập tk
    - Xem profile
    - Xem danh sách bài hát
    - Bài hát bao gồm
        - Ảnh bài hát
        - Tên bài
        - Tên ca sĩ
        - ID
    - Ứng dụng chạy nhạc kể cả khi đã Destroyed

*/


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val user: User = intent.getParcelableExtra("data")!!

    }
}