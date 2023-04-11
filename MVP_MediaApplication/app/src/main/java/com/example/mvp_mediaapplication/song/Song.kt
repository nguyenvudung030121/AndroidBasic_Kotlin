package com.example.mvp_mediaapplication.song

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Song(var id: Int, var title: String, var artis:String) : Parcelable {
}