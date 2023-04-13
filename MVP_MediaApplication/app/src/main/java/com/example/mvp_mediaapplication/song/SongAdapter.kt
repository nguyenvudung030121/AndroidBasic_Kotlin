package com.example.mvp_mediaapplication.song

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.mvp_mediaapplication.R

class SongAdapter(var listSong: MutableList<Song>, val listener: OnSongClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(data: Song) {
            itemView.findViewById<TextView>(R.id.txt_songTitle).text = data.title
            itemView.findViewById<TextView>(R.id.txt_songTitle).isSelected = true
            itemView.findViewById<TextView>(R.id.txt_songArtis).text = data.artis
            itemView.findViewById<ConstraintLayout>(R.id.song_layout).setOnClickListener {
                listener.onPlay(data)
            }
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


    interface OnSongClickListener {
        fun onPlay(song: Song)
    }

}