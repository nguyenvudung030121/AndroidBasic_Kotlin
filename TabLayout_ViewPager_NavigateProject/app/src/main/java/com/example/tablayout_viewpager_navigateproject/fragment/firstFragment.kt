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
import com.example.tablayout_viewpager_navigateproject.`interface`.SendData

class FirstFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        return view
    }

    fun recivedData(data: ItemFlower) {

        view?.findViewById<ImageView>(R.id.imgItem)?.setImageResource(data.img)
        view?.findViewById<TextView>(R.id.txt_ItemName)?.text = data.name

        val btn_add = view?.findViewById<Button>(R.id.btn_add)

        btn_add?.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                var sd:SendData = activity as SendData
                sd.sendData(data,2)
            }

        })

    }

}