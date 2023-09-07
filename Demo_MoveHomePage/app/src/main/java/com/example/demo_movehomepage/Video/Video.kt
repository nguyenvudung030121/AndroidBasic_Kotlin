package com.example.demo_movehomepage.Video

import android.provider.Telephony.Mms.Rate

class Video(
    val id: Int,
    val thumbnail:Int,
    var title: String,
    var category: String,
    var user: User,
    var timeOfVideo: String,
    var timeOfCategory: String,
    var timeRelease:String,
    var view_mount: String,
    var rate: Double
) {
}