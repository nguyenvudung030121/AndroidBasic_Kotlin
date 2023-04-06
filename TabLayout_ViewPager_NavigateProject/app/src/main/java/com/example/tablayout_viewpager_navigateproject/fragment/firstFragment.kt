package com.example.tablayout_viewpager_navigateproject.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.viewpager2.widget.ViewPager2
import com.example.tablayout_viewpager_navigateproject.R
import com.example.tablayout_viewpager_navigateproject.dataSource.ItemFlower
import com.example.tablayout_viewpager_navigateproject.databinding.ActivityMainBinding
import com.example.tablayout_viewpager_navigateproject.databinding.FragmentFirstBinding
import com.example.tablayout_viewpager_navigateproject.`interface`.SendData

class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFirstBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    fun recivedData(data: ItemFlower) {


        binding.imgItem.setImageResource(data.img)
        binding.txtItemName.text = data.name

        binding.btnAdd.setOnClickListener {
            (activity as SendData).sendData(data,2)
        }

    }

}