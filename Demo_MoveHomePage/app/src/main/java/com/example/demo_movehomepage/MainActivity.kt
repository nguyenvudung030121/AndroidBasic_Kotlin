package com.example.demo_movehomepage

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.demo_movehomepage.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var viewPager:AutoScrollViewPager? = null


    var fragmentList:ArrayList<FeatureFragment1> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        for (i in 1..4){
            fragmentList.add(FeatureFragment1())
        }

        //add them 2 cho vong lap infinite
/*        for (i in 1..3){
            fragmentList.add(FeatureFragment1())
        }*/
        setViewPagerAdapter()
    }

    private fun setViewPagerAdapter() {

        viewPager = binding.viewPager as AutoScrollViewPager
        viewPager!!.startAutoScroll();
        viewPager!!.interval = 3000
        viewPager!!.isCycle = true
        viewPager!!.isStopScrollWhenTouch = true

        var adapter: PagerAdapter =  CarouselAdapter(fragmentList)

        viewPager!!.adapter = adapter

    }



}