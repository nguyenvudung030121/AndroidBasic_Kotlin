package com.example.tablayout_viewpager_navigateproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.tablayout_viewpager_navigateproject.adapter.ItemAdapter
import com.example.tablayout_viewpager_navigateproject.adapter.ViewPagerAdapter
import com.example.tablayout_viewpager_navigateproject.dataSource.ItemFlower
import com.example.tablayout_viewpager_navigateproject.databinding.ActivityMainBinding
import com.example.tablayout_viewpager_navigateproject.fragment.FirstFragment
import com.example.tablayout_viewpager_navigateproject.fragment.SecondFragment
import com.example.tablayout_viewpager_navigateproject.`interface`.SendData
import com.google.android.material.tabs.TabLayoutMediator
class MainActivity : AppCompatActivity(),SendData {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =DataBindingUtil.setContentView(this,R.layout.activity_main)

        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)

        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabs,binding.viewPager){tab, position ->

            when(position){
                0->{
                    tab.text="List Item"
                }
                1->{
                    tab.text="Details"
                }
                2->{
                    tab.text="Item Added"
                }
            }

        }.attach()

    }

    override fun sendData(data: ItemFlower,index: Int) {

        if (index==1){
            val getfirstFragment = (binding.viewPager.adapter as ViewPagerAdapter).getFragmentByIndex(1)
            (getfirstFragment as FirstFragment).recivedData(data)
            binding.viewPager.currentItem = 1
        }else{
            val getSecondFragment = (binding.viewPager.adapter as ViewPagerAdapter).getFragmentByIndex(2)
            (getSecondFragment as SecondFragment).recivedData(data)
            binding.viewPager.currentItem = 2
        }


    }
}