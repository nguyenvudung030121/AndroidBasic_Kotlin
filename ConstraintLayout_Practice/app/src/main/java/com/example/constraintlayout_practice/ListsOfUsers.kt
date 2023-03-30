package com.example.constraintlayout_practice

import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
    var filterList:MutableList<User> = mutableListOf()
    lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_lists_of_users)

        binding.btnAddUser.setOnClickListener {
            dialogAddUser()
        }

        userDataList.add(User("123", "123", "Adung", "dd", "ee"))
        userDataList.add(User("123", "123", "Bdung", "dd", "ee"))
        userDataList.add(User("123", "123", "Bdung", "dd", "ee"))
        userDataList.add(User("123", "123", "Cdung", "dd", "ee"))
        userDataList.add(User("123", "123", "Cdung", "dd", "ee"))
        userDataList.add(User("123", "123", "A2dng", "dd", "ee"))


        userDataList.forEach { user ->
            if (title.find { it.name == user.name[0].toString() } == null) {
                title.add(User("123", "123", user.name[0].toString(), "dd", "ee", true))
            }
        }

        userDataList.addAll(title)
        userDataList.sortBy { it.name }

        // let, apply, run, ...
        binding.listUser.apply {
            layoutManager = LinearLayoutManager(this@ListsOfUsers)
            adapter = UserAdapter(userDataList)
        }

        binding.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {

                if (s.toString().equals("")) {
                    binding.listUser.apply {
                        adapter = UserAdapter(userDataList)
                    }
                } else {
//                    binding.listUser.apply {
//                        adapter = UserAdapter(userDataList.filter { user ->
//                            user.name.startsWith(
//                                s.toString().trim()
//                            )
//                        } as MutableList)

                    binding.listUser.apply {
                        var newAdapter:UserAdapter = UserAdapter(userDataList)
                        newAdapter.setFilterList(userDataList.filter { user ->
                            user.name.startsWith(
                                s.toString().trim()
                            )
                        } as MutableList)
                    }

                }

            }

        })

    }

    private fun checkTitle() {

        userDataList.removeAll(title)
        userDataList.forEach(System.out::println)

        title = mutableListOf()

        userDataList.forEach { user ->
            if (title.find { it.name == user.name[0].toString() } == null) {
                title.add(User("123", "123", user.name[0].toString(), "dd", "ee", true))
            }
        }

        userDataList.addAll(title)

        userDataList.sortBy { it.name }

        binding.listUser.apply {
            adapter = UserAdapter(userDataList)
        }
    }

    private fun dialogAddUser() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.fragment_add_user)
        val window = dialog.window ?: return
        window.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

/*        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val winAttributes = window.attributes
        window.attributes = winAttributes
        if (Gravity.BOTTOM == gravity) {
            dialog.setCancelable(true)
        } else {
            dialog.setCancelable(false)
        }*/

        val btn_add = dialog.findViewById<Button>(R.id.btn_Add)
        val btn_cancel = dialog.findViewById<Button>(R.id.btn_Cancel)

        btn_add.setOnClickListener {
            val edt_Username = dialog.findViewById<EditText>(R.id.edt_addUserName)
            if (edt_Username.text.toString().trim() != "") {
                val newName = edt_Username.text.toString()
                val newUser = User("123", "123", newName, "dd", "ee")
                userDataList.add(newUser)

                checkTitle()
            }

            dialog.dismiss()

        }

        btn_cancel.setOnClickListener {
            dialog.dismiss()
        }


        dialog.show()
    }
}