package com.example.shared_preferences

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.SharedPreferencesCompat
import androidx.databinding.DataBindingUtil
import com.example.shared_preferences.databinding.ActivityMainBinding
import java.lang.Integer.parseInt
import java.util.prefs.Preferences


/*
 - Sử dụng Shared Preferences nếu muốn sử dụng multiple references
 - Nếu chỉ sử dụng 1 Preferences thì chỉ nên sử dụng References

 - Shared Preferences được tạo ở trong onPause()
 - sử dụng getPreferences để khởi tạo (key,mode)
 - sử dụng .edit(). để truyền data vào
 - sau khi truyền xong thì sử dụng .edit().apply() để hoàn thành truyền data

 - Để nhận Shared thì sử dụng trong onResume() mỗi khi khởi tạo lại ứng dụng

*/

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        val getSharedPreferences = getPreferences(MODE_PRIVATE)

        var name = getSharedPreferences.getString("name","None of The Name")
        var age = getSharedPreferences.getInt("age",0)

        if (name != null) {
            Log.d("age@",name)
        }

        binding.edtName.setText(name)
        binding.edtAge.setText(age.toString())

    }

    override fun onPause() {
        super.onPause()
        val sharedPreferences = getPreferences(MODE_PRIVATE)

        sharedPreferences.edit().putString("name",binding.edtName.text.toString())
        sharedPreferences.edit().putInt("age",parseInt(binding.edtAge.text.toString()))

        sharedPreferences.edit().apply()

        Log.d("age@",binding.edtName.text.toString())
    }
}