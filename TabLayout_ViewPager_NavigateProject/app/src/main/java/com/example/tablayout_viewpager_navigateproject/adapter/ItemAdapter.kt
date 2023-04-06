package com.example.tablayout_viewpager_navigateproject.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.tablayout_viewpager_navigateproject.R
import com.example.tablayout_viewpager_navigateproject.dataSource.ItemFlower
import com.example.tablayout_viewpager_navigateproject.`interface`.SendData


class ItemAdapter(var listItem:MutableList<ItemFlower>,var viewType: Int):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
            fun onBind(data:ItemFlower,viewType: Int){
                itemView.findViewById<ImageView>(R.id.imgItem).setImageResource(data.img)
                itemView.findViewById<TextView>(R.id.txt_ItemName).text = data.name

                itemView.findViewById<CardView>(R.id.cardViewItem).setOnClickListener {
                    var activity = itemView.context as AppCompatActivity

                    var sd: SendData = activity as SendData

                    sd.sendData(data, 1)
                }
            }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1) {
            return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
            )
        } else {
            return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_added, parent, false)
            )

        }
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as ViewHolder).onBind(listItem[position],viewType)
    }

    override fun getItemViewType(position: Int): Int {
        return viewType
    }

}