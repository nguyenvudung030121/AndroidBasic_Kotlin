package com.example.tablayout_viewpager_navigateproject.fragment

import android.content.ClipData.Item
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tablayout_viewpager_navigateproject.R
import com.example.tablayout_viewpager_navigateproject.adapter.ItemAdapter
import com.example.tablayout_viewpager_navigateproject.dataSource.ItemFlower

class SecondFragment : Fragment() {

    private var itemAddedList: MutableList<ItemFlower> = mutableListOf()
    lateinit var adapterItem: ItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_second, container, false)
        setUpList()
        return view
    }

    fun recivedData(data: ItemFlower) {
        itemAddedList.add(data)
        setUpList()
    }

    fun setUpList(){
        val listOfItem = view?.findViewById<RecyclerView>(R.id.listOfItemAdded)
        adapterItem = ItemAdapter(itemAddedList,2)
        listOfItem?.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        listOfItem?.adapter = adapterItem
    }

}