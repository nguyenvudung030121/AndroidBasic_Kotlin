package com.example.shared_preferences

import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.SharedPreferencesCompat
import androidx.databinding.DataBindingUtil
import com.example.shared_preferences.databinding.ActivityMainBinding
import com.google.gson.Gson
import java.lang.Integer.parseInt
import java.util.prefs.Preferences


/*
 - Sử dụng Shared Preferences nếu muốn sử dụng multiple references
 - Nếu chỉ sử dụng 1 Preferences thì chỉ nên sử dụng References
 - Sử dụng EncryptedSharedPreferences để Secure hơn

 - Shared Preferences được tạo ở trong onPause()
 - sử dụng getPreferences để khởi tạo (key,mode)
 - sử dụng .edit(). để truyền data vào
 - sau khi truyền xong thì sử dụng .edit().apply() để hoàn thành truyền data

 - Để nhận Shared thì sử dụng trong onResume() mỗi khi khởi tạo lại ứng dụng
 - Để truyền một Object bằng Preferences thì sử dụng Gson & Json

 - Để Clear 1 References:
    - <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    - .editor().clear().apply()


*/

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private var tokenUser:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.btnSendData.setOnClickListener {
            startActivity(Intent(this,SecondActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()

        val getSharedPreferences = getSharedPreferences("login",MODE_PRIVATE)
        val name = getSharedPreferences.getString("name","None of The Name")
        val age = getSharedPreferences.getInt("age",0)

        if (!name.equals("None of The Name") && age != 0){
            startActivity(Intent(this,SecondActivity::class.java))
        }


        binding.edtName.setText(name)
        binding.edtAge.setText(age.toString())

    }

    override fun onPause() {
        super.onPause()

        val user = User(binding.edtName.text.toString(),parseInt(binding.edtAge.text.toString()))
        val gson = Gson()
        val jsonString = gson.toJson(user)



        val sharedPreferences = this.getSharedPreferences("login",MODE_PRIVATE)
        val edit:Editor = sharedPreferences.edit()

        with (sharedPreferences.edit()) {
            putString("name",binding.edtName.text.toString())
            putInt("age",parseInt(binding.edtAge.text.toString()))
            putString("user",jsonString)
            apply()
        }

/*      edit.putString("name",binding.edtName.text.toString())
        edit.putInt("age",parseInt(binding.edtAge.text.toString()))
        edit.apply()*/

/*
        val name = sharedPreferences.getString("name","default")
        if (name != null) {
            Log.d("age@",name)
        }*/

    }
}