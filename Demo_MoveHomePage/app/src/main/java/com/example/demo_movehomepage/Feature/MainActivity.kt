package com.example.demo_movehomepage.Feature

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.demo_movehomepage.R
import com.example.demo_movehomepage.databinding.ActivityMainBinding
import kotlin.math.abs


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var viewPager2Adapter: ViewPager2Adapter
    private lateinit var handler: Handler


    var fragmentList: ArrayList<FeatureFragment1> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setViewPagerAdapter()
        setUpTransformer()

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 3000)
            }
        })

    }


    private fun setViewPagerAdapter() {

        for (i in 1..4) {
            fragmentList.add(FeatureFragment1())
        }

        handler = Handler(Looper.myLooper()!!)
        viewPager2Adapter = ViewPager2Adapter(fragmentList, binding.viewPager)

        binding.viewPager.apply {
            adapter = viewPager2Adapter
            offscreenPageLimit = 3
            clipToPadding = false
            clipChildren = false
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }

    }
    private val runnable = Runnable {
        binding.viewPager.currentItem = binding.viewPager.currentItem + 1
    }

    private fun setUpTransformer() {
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))

        // do mình thực hiện xoay màn hình nên cần lấy height chứ ko phải width
        val screenHeight = resources.displayMetrics.widthPixels
        val nextItemTranslationX = 5f * screenHeight / 60

        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleX = 0.85f + r * 0.1f
            // translation X
            page.translationX = -position * nextItemTranslationX
        }
        binding.viewPager.setPageTransformer(transformer)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable, 3000)
    }


}