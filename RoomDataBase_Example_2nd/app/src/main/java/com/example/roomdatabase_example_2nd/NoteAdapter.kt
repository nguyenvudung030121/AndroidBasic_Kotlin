package com.example.roomdatabase_example_2nd

import android.provider.ContactsContract.CommonDataKinds.Note
import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabase_example_2nd.room.NoteModel

class NoteAdapter(private var list: List<NoteModel>, var listener: Listeners) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        fun onBind(noteModel: NoteModel){
            itemView.findViewById<TextView>(R.id.tv_title).text = noteModel.title
            itemView.findViewById<TextView>(R.id.tv_desc).text = noteModel.description

            itemView.findViewById<CardView>(R.id.layout).setOnClickListener {
                    listener.onClicked(noteModel)
            }



        }
    }

    interface Listeners {
        fun onClicked(note: NoteModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note,parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

}