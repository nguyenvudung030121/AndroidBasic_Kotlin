package com.example.mvp_mediaapplication.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.example.mvp_mediaapplication.R
import com.example.mvp_mediaapplication.dataSource.User
import com.example.mvp_mediaapplication.databinding.ActivityMainBinding
import com.example.mvp_mediaapplication.fragment.Home_Fragment
import com.example.mvp_mediaapplication.song.Song_Fragment
import com.example.mvp_mediaapplication.fragment.UserProfile_Fragment


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


    - Create Bottom navigation view:
        - Declare bottom navigation view
        - Create Menu in res
        - Create menu item with: Id,Title,Icon
        - set item for menu: - app:menu = menu in res
        - set Item background for nav
        - set color for itemTint & itemText Color = create Color in Res

*/


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val homeFragment: Home_Fragment = Home_Fragment()
    private val songFragment: Song_Fragment = Song_Fragment()
    private val profileUserFragment: UserProfile_Fragment = UserProfile_Fragment()
    private val fragmentList = arrayListOf(homeFragment, songFragment, profileUserFragment)
    lateinit var user: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        user = intent.getParcelableExtra("data")!!

        setAdapterForViewPager()

        sendDataToFragment(user)


    }

    fun sendDataToFragment(user: User) {

        val bundle = Bundle()
        bundle.putParcelable("data", user)
        profileUserFragment.arguments = bundle

    }

    fun setAdapterForViewPager() {
        val adapter = ViewPagerAdapter(fragmentList, supportFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter
        viewPagerOnChange()
        navigatorChange()
    }


    fun viewPagerOnChange() {
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        binding.navigation.menu.findItem(R.id.action_home).isChecked = true
                    }
                    1 -> {
                        binding.navigation.menu.findItem(R.id.action_song).isChecked = true
                    }
                    2 -> {
                        binding.navigation.menu.findItem(R.id.action_userProfile).isChecked = true
                    }
                }
            }
        })
    }

    fun navigatorChange() {

        binding.navigation.setOnItemSelectedListener {

            when (it.itemId) {
                R.id.action_home -> {
                    binding.viewPager.currentItem = 0
                }

                R.id.action_song -> {
                    binding.viewPager.currentItem = 1
                }

                R.id.action_userProfile -> {
                    binding.viewPager.currentItem = 2
                }
            }
            return@setOnItemSelectedListener true
        }


    }

}