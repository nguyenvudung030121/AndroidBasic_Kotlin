package com.example.demo_movehomepage

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class CarouselAdapter(var fragmentCarousel: ArrayList<FeatureFragment1>,fragmentManager: FragmentManager,lifecycle: Lifecycle)
    : FragmentStateAdapter(fragmentManager,lifecycle) {
    override fun getItemCount(): Int {
       return fragmentCarousel.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentCarousel[position]
    }


}