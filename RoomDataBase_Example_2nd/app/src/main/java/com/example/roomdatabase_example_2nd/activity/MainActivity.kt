package com.example.roomdatabase_example_2nd.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.roomdatabase_example_2nd.R

/*
    - Impletement những thư viện cần thiết
    - Tạo 1 Model entity
    - Tạo Dao cho entity
    - Tạo abstract class database để đưa entity vào
    - Trong một abstract class database tạo 1 abstract
    - Tạo instance cho database (phải được khai báo là static):
        - Vì nó chỉ cần tạo 1 lần duy nhất tránh lãng phí bộ nhớ
        - Để tạo nó thì cần 1 biến INstance check xem nó đc tạo hay chưa
        - Rồi kiểm tra xem biến INSTANCE đó có null hay không
        - Nếu không thì Đặt khởi tạo instance trong một synchronlize (đồng bộ 1 luồng duy nhất)

    - Tại Main:
        - Khởi tạo instance cho database

*/

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}