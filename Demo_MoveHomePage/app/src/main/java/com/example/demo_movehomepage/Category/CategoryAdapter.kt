package com.example.demo_movehomepage.Category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demo_movehomepage.R

class CategoryAdapter(var listCategory:MutableList<Category>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ViewHolder(viewItem:View):RecyclerView.ViewHolder(viewItem){
        fun onBind(category: Category){
            itemView.findViewById<AppCompatImageView>(R.id.img_category).setImageResource(category.img)
            itemView.findViewById<AppCompatTextView>(R.id.txt_categoryType).text = category.type
            itemView.findViewById<AppCompatTextView>(R.id.txt_categoryView).text = category.view
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_category,parent,false))
    }

    override fun getItemCount(): Int {
       return listCategory.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).onBind(listCategory[position])
    }
}