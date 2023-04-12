package com.example.mvp_mediaapplication.main

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.webkit.MimeTypeMap
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import android.Manifest
import android.webkit.PermissionRequest
import android.widget.Toast
import com.example.mvp_mediaapplication.R
import com.example.mvp_mediaapplication.dataSource.User
import com.example.mvp_mediaapplication.databinding.ActivityMainBinding
import com.example.mvp_mediaapplication.fragment.Home_Fragment
import com.example.mvp_mediaapplication.fragment.UserProfile_Fragment
import com.example.mvp_mediaapplication.song.Song_Fragment
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.single.PermissionListener


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
    - Ứng dụng chạy nhạc trong nền


    - Create Bottom navigation view:
        - Declare bottom navigation view
        - Create Menu in res
        - Create menu item with: Id,Title,Icon
        - set item for menu: - app:menu = menu in res
        - set Item background for nav
        - set color for itemTint & itemText Color = create Color in Res


    - Xử lý ViewPager2:
        - Tạo adapter Cho ViewPager2
        - Tạo danh sách Fragment rồi đưa vào adapter
        - Gán adapter của ViewPager2 vào adapter vừa tạo
        - Xử lý Navigation change Fragment:
            - xử lý sự kiện bằng navigation.SetOnItemSelectedListener
            - Set currentItem của ViewPager cho mỗi lần click vào mỗi Menu

        - Xử lý lướt trên ViewPager2 -> BottomMenu selectedItem cũng đổi theo:
            - xử lý sự kiện bằng viewPager.registerOnChangeCallBack(object -> ViewPager2.OnChangeCallBack)
            - Override trong OnSelected -> navigation.menu.findItem(id).isChecked = true/false


    - Tạo RecyclerView để show danh sách bài hát:
        - Tạo đối tượng bài hát: ID,title,artis,...
        - CustomViewItem cho 1 bài hát
        - Tạo adapter recyclerView
        - Tạo SongView để xử lý sự kiện trên View của Song_Fragment
        - Tạo SongPresenter để lấy dữ liệu bài hát


    - Lấy dữ liệu Bài hát từ Storage:
        - Add user-permission trong Manifest : READ_EXTERNAL_STORAGE
        - Add user-permission trong Manifest : MANAGE_EXTERNAL_STORAGE
        - Sử dụng MediaStore?
        - Add 'com.karumi:dexter:6.2.3' for auto use storage permission
        - Dùng SongPresenter getData từ local
        - Qua Song_Fragment nhận data

    - Set up Media Player:
        - If your player application needs to keep the screen from dimming or the processor from sleeping
            or uses the MediaPlayer.setScreenOnWhilePlaying() or MediaPlayer.setWakeMode() methods
            you must request this permission.

        - Dùng MediaPlayer để chạy nhạc


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
        runTimePermission()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        user = intent.getParcelableExtra("data")!!

        setAdapterForViewPager()

        sendDataToFragment(user)



        /*val musicList = getMusicList()

        musicList?.forEach(System.out::println)*/
    }



    fun runTimePermission(){
        Dexter.withActivity(this)
            .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse?) {

                }

                override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: com.karumi.dexter.listener.PermissionRequest?,
                    p1: PermissionToken?
                ) {

                }
            }
            ).check()

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