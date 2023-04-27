package com.example.demo_movehomepage.Video

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.demo_movehomepage.R

class VideoAdapter(var listVideo:MutableList<Video>):RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    inner class ViewHolder(viewItem:View):RecyclerView.ViewHolder(viewItem){
        fun onBind(video: Video){
            itemView.findViewById<ImageView>(R.id.thumbnail_2).setImageResource(video.thumbnail)
            itemView.findViewById<TextView>(R.id.videomaylike_view_count).text = video.view_mount
            itemView.findViewById<TextView>(R.id.videomaylike_time).text = video.timeOfVideo
            itemView.findViewById<ImageView>(R.id.videoyoulike_user_avatar).setImageResource(video.user.avatar)
            itemView.findViewById<TextView>(R.id.videomaylike_user_name).text = video.user.name
            itemView.findViewById<TextView>(R.id.videomaylike_videoTitle).text = video.title
            itemView.findViewById<TextView>(R.id.videomaylike_category).text = video.category




        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_videoyoulike,parent,false))
    }

    override fun getItemCount(): Int {
        return listVideo.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).onBind(listVideo[position])
    }

}