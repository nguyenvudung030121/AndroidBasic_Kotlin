package com.example.constraintlayout_practice

import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.constraintlayout_practice.databinding.ActivityListsOfUsersBinding

class ListsOfUsers : AppCompatActivity() {
    private lateinit var binding: ActivityListsOfUsersBinding
    private var userDataList: MutableList<User> = mutableListOf()
    private var title: MutableList<User> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_lists_of_users)

        binding.btnAddUser.setOnClickListener{
            dialogAddUser()
        }


        val user1 = User("123", "123", "Adung", "dd", "ee")
        val user2 = User("123", "123", "A2dng", "dd", "ee")
        val user3 = User("123", "123", "Bdung", "dd", "ee")
        val user4 = User("123", "123", "Bdung", "dd", "ee")
        val user5 = User("123", "123", "Cdung", "dd", "ee")
        val user6= User("123", "123", "Cdung", "dd", "ee")

        userDataList.add(user1)
        userDataList.add(user2)
        userDataList.add(user3)
        userDataList.add(user4)
        userDataList.add(user5)
        userDataList.add(user6)


        userDataList.forEach { user ->
            if (title.find { it.name == user.name[0].toString() } == null) {
                title.add(User("123", "123", user.name[0].toString(), "dd", "ee", true))
            }
        }

        userDataList.addAll(title)
        userDataList.sortBy { it.name}

        // let, apply, run, ...
        binding.listUser.apply {
            layoutManager = LinearLayoutManager(this@ListsOfUsers)
            adapter = UserAdapter(userDataList)
        }

    }

    private fun 

    private fun dialogAddUser(){
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.fragment_add_user)
        val window = dialog.window ?: return
        window.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

//        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        val winAttributes = window.attributes
//        window.attributes = winAttributes
//        if (Gravity.BOTTOM == gravity) {
//            dialog.setCancelable(true)
//        } else {
//            dialog.setCancelable(false)
//        }

        val btn_add = dialog.findViewById<Button>(R.id.btn_Add)
        val btn_cancel = dialog.findViewById<Button>(R.id.btn_Cancel)

        btn_add.setOnClickListener{
            val edt_Username = dialog.findViewById<EditText>(R.id.edt_addUserName)
            var newName = edt_Username.text.toString()
            userDataList.add(User("123", "123",newName, "dd", "ee"))
            dialog.dismiss()

        }

        btn_cancel.setOnClickListener {
            dialog.dismiss()
        }


        dialog.show()
    }
}