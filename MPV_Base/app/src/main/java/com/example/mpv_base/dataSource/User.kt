package com.example.mpv_base.dataSource

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(val email:String,val password:String,val name:String,val dob:String,val addr:String, var isTitle: Boolean = false) :
    Parcelable {

}