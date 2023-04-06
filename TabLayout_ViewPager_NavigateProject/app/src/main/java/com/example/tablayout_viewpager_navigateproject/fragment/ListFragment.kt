package com.example.tablayout_viewpager_navigateproject.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.FragmentContainer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tablayout_viewpager_navigateproject.R
import com.example.tablayout_viewpager_navigateproject.adapter.ItemAdapter
import com.example.tablayout_viewpager_navigateproject.dataSource.ItemFlower

class ListFragment : Fragment() {
    private var itemDataList:MutableList<ItemFlower> = mutableListOf()
    lateinit var itemAdapter: ItemAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        getDataItem()
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        val listOfItem = view.findViewById<RecyclerView>(R.id.listOfItem)
        val searchView = view.findViewById<SearchView>(R.id.searchView)


        itemAdapter = ItemAdapter(itemDataList,1)
        listOfItem.layoutManager = GridLayoutManager(activity,2)
        listOfItem.adapter = itemAdapter


        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                filterList(newText,listOfItem)

                return true
            }

        })



        return view
    }

    private fun filterList(newText: String?, listOfItem:RecyclerView) {
        if (newText != null ){
            var listItemFilter:MutableList<ItemFlower> =
                itemDataList.filter { itemFlower -> itemFlower.name.lowercase().contains(newText.toString()) } as MutableList

            if(!listItemFilter.isEmpty()){
                itemAdapter = ItemAdapter(listItemFilter,1)
                listOfItem.layoutManager = GridLayoutManager(activity,2)
                listOfItem.adapter = itemAdapter
            }

        }
    }



    fun getDataItem(){
        itemDataList.add(ItemFlower(R.drawable.hoahong,"Hoa Hong"))
        itemDataList.add(ItemFlower(R.drawable.hoaanhdao,"Hoa Anh Dao"))
        itemDataList.add(ItemFlower(R.drawable.hoacuc,"Hoa Cuc"))
        itemDataList.add(ItemFlower(R.drawable.hoamai,"Hoa Mai"))
        itemDataList.add(ItemFlower(R.drawable.hoahuongduong,"Hoa Huong"))
        itemDataList.add(ItemFlower(R.drawable.hoalan,"Hoa Lan"))


        itemDataList.add(ItemFlower(R.drawable.hoahong,"Hoa Hong"))
        itemDataList.add(ItemFlower(R.drawable.hoaanhdao,"Hoa Anh Dao"))
        itemDataList.add(ItemFlower(R.drawable.hoacuc,"Hoa Cuc"))
        itemDataList.add(ItemFlower(R.drawable.hoamai,"Hoa Mai"))
        itemDataList.add(ItemFlower(R.drawable.hoahuongduong,"Hoa Huong"))
        itemDataList.add(ItemFlower(R.drawable.hoalan,"Hoa Lan"))
    }
}