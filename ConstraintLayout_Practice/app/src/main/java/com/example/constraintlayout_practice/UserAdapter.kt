package com.example.constraintlayout_practice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(var listUser: MutableList<User> = mutableListOf()) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TITLE_ITEM: Int = 0
        const val ITEM: Int = 1
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(data: User) {
            itemView.findViewById<TextView>(R.id.txt_username).text = data.name
        }
    }

    class TitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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
            holder.onBind(listUser[position])
        } else {
            val user = listUser[position]
            (holder as ViewHolder).onBind(user)

            holder.itemView.findViewById<ImageView>(R.id.btn_Delete).setOnClickListener {
                removeAtUser(position)
                var pos = position
                if (position == listUser.size) pos = position - 1

                checkItemHasNullUser(pos)
            }
        }
    }

    open fun setFilterList(userListFilter: MutableList<User>){
        this.listUser = userListFilter
        listUser.forEach(System.out::println)
        notifyDataSetChanged()
    }

    open fun checkItemHasNullUser(position: Int) {
        if (listUser.last().isTitle) {
            removeAtUser(listUser.size - 1)
        } else if (listUser[position].isTitle && listUser[position - 1].isTitle) {
            removeAtUser(position - 1)
        }
    }


    open fun removeAtUser(position: Int) {
        listUser.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, listUser.size)
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (listUser[position].isTitle) TITLE_ITEM else ITEM
    }
}