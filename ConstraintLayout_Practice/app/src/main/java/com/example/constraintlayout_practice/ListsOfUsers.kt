package com.example.constraintlayout_practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.constraintlayout_practice.databinding.ActivityListsOfUsersBinding
import com.example.constraintlayout_practice.databinding.ActivityUserProfileBinding

class ListsOfUsers : AppCompatActivity() {
    private lateinit var binding: ActivityListsOfUsersBinding
    var userDataList: MutableList<User> = mutableListOf()
    var listUser: MutableList<User> = mutableListOf()

    var title: MutableList<User> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_lists_of_users)

        val user1 = User("123", "123", "Adung", "dd", "ee")
        val user2 = User("123", "123", "A2dng", "dd", "ee")
        val user3 = User("123", "123", "Bdung", "dd", "ee")
        val user4 = User("123", "123", "Cdung", "dd", "ee")

        userDataList.add(user1)
        userDataList.add(user2)
        userDataList.add(user3)
        userDataList.add(user4)


        userDataList.forEach { user ->
            if (title.find { it.name == user.name[0].toString() } == null) {
                title.add(User("123", "123", user.name[0].toString(), "dd", "ee", true))
            }

//            if (user.name[0].toString() !in title) {
//                    user.isTitle = true
//                    title+=user.name[0].toString()
//                    userDataList.add(user)
//
//            }
        }

        userDataList.addAll(title)

        userDataList.sortBy { it.name }

        // let, apply, run, ...
        binding.listUser.apply {
            layoutManager = LinearLayoutManager(this@ListsOfUsers)
            adapter = UserAdapter(userDataList)
        }

    }
}