package com.example.mvp_mediaapplication.song

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvp_mediaapplication.R

class SongAdapter(var listSong: MutableList<Song>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(data: Song) {
            itemView.findViewById<TextView>(R.id.txt_songTitle).text = data.title
            itemView.findViewById<TextView>(R.id.txt_songTitle).isSelected = true

            itemView.findViewById<TextView>(R.id.txt_songArtis).text = data.artis
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_song, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).onBind(listSong[position])
    }

    override fun getItemCount(): Int {
        return listSong.size
    }
}