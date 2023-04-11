package com.example.mvp_mediaapplication.song

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SongPresenter(var songView: SongView) {


   fun onShowListOfSong(){
       songView.onShowListOfSong()
   }



}