package com.example.demo_movehomepage.Video

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demo_movehomepage.R

class VideoAdapter(var listVideo:MutableList<Video>):RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    inner class ViewHolder(viewItem:View):RecyclerView.ViewHolder(viewItem){
        fun onBind(video: Video){
            itemView.findViewById<ImageView>(R.id.img_videoYouMayLikeThumbnail).setImageResource(video.thumbnail)
            itemView.findViewById<TextView>(R.id.txt_videoYouMayLike_view_count).text = video.view_mount
            itemView.findViewById<TextView>(R.id.txt_videoYouMayLikeTime).text = video.timeOfCategory
            itemView.findViewById<ImageView>(R.id.img_videoYouMayLike_user_avatar).setImageResource(video.user.avatar)
            itemView.findViewById<TextView>(R.id.txt_videoYouMayLike_user_name).text = video.user.name
            itemView.findViewById<TextView>(R.id.txt_videoYouMayLike_videoTitle).text = "${video.title}"
            itemView.findViewById<TextView>(R.id.txt_videoYouMayLike_category).text = "${video.category} • A day ago"

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