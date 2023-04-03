package com.example.tablayout_viewpager_navigateproject.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.tablayout_viewpager_navigateproject.fragment.FirstFragment
import com.example.tablayout_viewpager_navigateproject.fragment.ListFragment
import com.example.tablayout_viewpager_navigateproject.fragment.SecondFragment

class ViewPagerAdapter(fragment: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragment,lifecycle) {

    private val fragmentList = arrayListOf(ListFragment(), FirstFragment(), SecondFragment())

    override fun getItemCount() = fragmentList.size

    override fun createFragment(position: Int) = fragmentList[position]

    fun getFragmentByIndex(index: Int) = fragmentList[index]
}