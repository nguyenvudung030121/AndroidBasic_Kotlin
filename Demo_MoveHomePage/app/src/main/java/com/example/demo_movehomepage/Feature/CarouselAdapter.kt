package com.example.demo_movehomepage.Feature

import android.view.View
import androidx.viewpager.widget.PagerAdapter


class CarouselAdapter(var fragmentCarousel: ArrayList<FeatureFragment1>):PagerAdapter() {
    override fun getCount(): Int {
        return fragmentCarousel.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as View
    }



}