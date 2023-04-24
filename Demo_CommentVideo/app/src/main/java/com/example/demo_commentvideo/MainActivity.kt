package com.example.demo_commentvideo

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demo_commentvideo.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), CommentListener {
    lateinit var binding: ActivityMainBinding
    lateinit var adapterComment: listCommentAdapter
    private var listComment: MutableList<User> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        userComment(binding.cancelButton, binding.sendButton, binding.edtUserComment, listComment)
    }


    override fun userComment(
        cancelButton: AppCompatButton,
        sendButton: AppCompatButton,
        editText: AppCompatEditText,
        listComment: MutableList<User>
    ) {
        cancelButton.visibility = View.GONE
        sendButton.visibility = View.GONE
        onWriteCommentListener(editText, cancelButton, sendButton)
        onCancelUserComment(cancelButton, editText)
        onSendUserComment(sendButton, listComment, editText, cancelButton)
        onLoadComment(listComment)

    }

    override fun onWriteCommentListener(
        editText: AppCompatEditText,
        cancelButton: AppCompatButton,
        sendButton: AppCompatButton
    ) {

        editText.apply {
            addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (!s.isNullOrEmpty()) {
                        cancelButton.visibility = View.VISIBLE
                        sendButton.visibility = View.VISIBLE
                    } else {
                        cancelButton.visibility = View.GONE
                        sendButton.visibility = View.GONE
                    }
                }

                override fun afterTextChanged(s: Editable?) {
/*                if (!s.isNullOrEmpty()) {
                    binding.edtUserComment.clearFocus()
                }*/
                }

            })


            onFocusChangeListener =
                View.OnFocusChangeListener { v, hasFocus ->
                    if (!hasFocus) {
                        v?.let { hideKeyboard(it) }
                    }
                }
        }

    }

    override fun hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onCancelUserComment(cancelButton: AppCompatButton, editText: AppCompatEditText) {
        cancelButton.setOnClickListener {
            clearEdittext(editText, cancelButton)
        }
    }

    override fun onSendUserComment(
        sendButton: AppCompatButton,
        listComment: MutableList<User>,
        editText: AppCompatEditText,
        cancelButton: AppCompatButton
    ) {
        sendButton.setOnClickListener {
            listComment.add(
                0,
                User(
                    R.drawable.avatar,
                    "Nguyen Vu Dung",
                    editText.text.toString().trim(),
                    "Just Now",
                    true
                )
            )
            onLoadComment(listComment)
            clearEdittext(editText, cancelButton)
        }
    }

    override fun clearEdittext(editText: AppCompatEditText, cancelButton: AppCompatButton) {
        editText.text = null
        editText.clearFocus()
        val imm: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(
            cancelButton.windowToken,
            InputMethodManager.RESULT_UNCHANGED_SHOWN
        )
    }

    override fun onLoadComment(listComment: MutableList<User>) {
        getData()
        adapterComment = listCommentAdapter(listComment, object : listCommentAdapter.ReplyListener {
            override fun userComment(
                cancelButton: AppCompatButton,
                sendButton: AppCompatButton,
                editText: AppCompatEditText,
                listComment: MutableList<User>
            ) {
                cancelButton.visibility = View.GONE
                sendButton.visibility = View.GONE
                onWriteCommentListener(editText, cancelButton, sendButton)
                onCancelUserComment(cancelButton, editText)
                onSendUserComment(sendButton, listComment, editText, cancelButton)
            }

            override fun onWriteCommentListener(
                editText: AppCompatEditText,
                cancelButton: AppCompatButton,
                sendButton: AppCompatButton
            ) {
                editText.apply {
                    addTextChangedListener(object : TextWatcher {
                        override fun beforeTextChanged(
                            s: CharSequence?,
                            start: Int,
                            count: Int,
                            after: Int
                        ) {
                        }

                        override fun onTextChanged(
                            s: CharSequence?,
                            start: Int,
                            before: Int,
                            count: Int
                        ) {
                            if (!s.isNullOrEmpty()) {
                                cancelButton.visibility = View.VISIBLE
                                sendButton.visibility = View.VISIBLE
                            } else {
                                cancelButton.visibility = View.GONE
                                sendButton.visibility = View.GONE
                            }
                        }

                        override fun afterTextChanged(s: Editable?) {
/*                if (!s.isNullOrEmpty()) {
                    binding.edtUserComment.clearFocus()
                }*/
                        }

                    })


                    onFocusChangeListener =
                        View.OnFocusChangeListener { v, hasFocus ->
                            if (!hasFocus) {
                                v?.let { hideKeyboard(it) }
                            }
                        }
                }

            }

            override fun onCancelUserComment(
                cancelButton: AppCompatButton,
                editText: AppCompatEditText
            ) {
                cancelButton.setOnClickListener {
                    clearEdittext(editText, cancelButton)
                }
            }

            override fun onSendUserComment(
                sendButton: AppCompatButton,
                listComment: MutableList<User>,
                editText: AppCompatEditText,
                cancelButton: AppCompatButton
            ) {
                sendButton.setOnClickListener {
                    listComment.add(
                        0,
                        User(
                            R.drawable.avatar,
                            "Nguyen Vu Dung",
                            editText.text.toString().trim(),
                            "Just Now",
                            true
                        )
                    )
                    onLoadComment(listComment)
                    clearEdittext(editText, cancelButton)
                }
            }

            override fun clearEdittext(editText: AppCompatEditText, cancelButton: AppCompatButton) {
                editText.text = null
                editText.clearFocus()
                val imm: InputMethodManager =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(
                    cancelButton.windowToken,
                    InputMethodManager.RESULT_UNCHANGED_SHOWN
                )
            }

            override fun hideKeyboard(view: View) {
                val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            }


        })

        binding.listComment.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = adapterComment
        }
    }

    private fun getData() {
        listComment.add(User(R.drawable.avatar, "Nguyen Dung", "Hi Chao Cau", "Just Now", true))
        listComment.add(
            User(
                R.drawable.avatar,
                "Michael Jackson",
                "Lorem ipsum dolor sit amet, consetetur sadipscing elitr," +
                        " sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat," +
                        " sed diam voluptua. At vero eos et accusam et justo duo dolores.",
                "Just Now",
                false
            )
        )
        listComment.add(
            User(
                R.drawable.avatar,
                "Taylor Swift",
                "Lorem ipsum dolor sit amet, consetetur sadipscing elitr," +
                        " sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat" +
                        ", sed diam voluptua. At vero eos et accusam et justo duo dolores.",
                "Just Now",
                true
            )
        )
        listComment.add(User(R.drawable.avatar, "Justin Bieber", "Hi Chao Cau", "Just Now", false))
        listComment.add(User(R.drawable.avatar, "Nguyen Vu Dung", "Hi Chao Cau", "Just Now", true))
        listComment.add(User(R.drawable.avatar, "Nguyen Vu Dung", "Hi Chao Cau", "Just Now", true))

    }
}