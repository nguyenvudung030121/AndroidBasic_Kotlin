package com.example.demo_movehomepage

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager


class CarouselAdapter(var fragmentCarousel: ArrayList<FeatureFragment1>):PagerAdapter() {
    override fun getCount(): Int {
        return fragmentCarousel.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as View
    }



}