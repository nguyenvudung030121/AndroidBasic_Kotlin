package com.example.constraintlayout_practice

import android.service.quicksettings.Tile
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(var listUser: MutableList<User> = mutableListOf()) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TITLE_ITEM: Int = 0
        const val ITEM: Int = 1
    }

//
//    fun sortTitle(title: String): String {
//        if (!title.equals("")) {
//            var str = title.split("").sorted().joinToString("")
//            return str
//        }
//        return title
//    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(data: User) {
            itemView.findViewById<TextView>(R.id.txt_username).text = data.name
        }
    }

    inner class TitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(data: User) {
            itemView.findViewById<TextView>(R.id.txt_Title).text = data.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TITLE_ITEM -> TitleViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_title, parent, false)
            )
            else -> ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TitleViewHolder) {
            holder.onBind(listUser.get(position))
        } else {
            val user = listUser.get(position)
            (holder as ViewHolder).onBind(user)
        }
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (listUser[position].isTitle) TITLE_ITEM else ITEM
    }
}