package com.example.roomdatabase_example_2nd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/*
    - Impletement những thư viện cần thiết
    - Tạo 1 Model entity
    - Tạo Dao cho entity
    - Tạo database để đưa entity vào
    - Tạo instance cho database
    -

*/

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}