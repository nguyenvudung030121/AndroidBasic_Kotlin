package com.example.demo_movehomepage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.demo_movehomepage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val handler = Handler()
    var fragmentList:ArrayList<FeatureFragment1> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        for (i in 1..4){
            fragmentList.add(FeatureFragment1())
        }
        setViewPagerAdapter()
    }

    private fun setViewPagerAdapter() {
        var adapter = CarouselAdapter(fragmentList, supportFragmentManager,lifecycle)
        binding.viewPager.adapter = adapter

    }
}