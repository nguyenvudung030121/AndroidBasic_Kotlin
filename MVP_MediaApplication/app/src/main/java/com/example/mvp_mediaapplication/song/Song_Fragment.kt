package com.example.mvp_mediaapplication.song

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvp_mediaapplication.databinding.FragmentSongBinding
import com.example.mvp_mediaapplication.main.MainActivity


class Song_Fragment : Fragment(), SongView {
    lateinit var binding: FragmentSongBinding
    lateinit var songPresenter: SongPresenter
    lateinit var songAdapter: SongAdapter
    var listOfSong:MutableList<Song>? = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSongBinding.inflate(inflater)
        songPresenter = SongPresenter(this)
        songPresenter.onShowListOfSong()

        listOfSong = activity?.let { songPresenter.getMusicList(it.applicationContext) }


        return binding.root
    }



    override fun onShowListOfSong() {
        songAdapter = listOfSong?.let { SongAdapter(it) }!!

        binding.listSong.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

        binding.listSong.adapter = songAdapter
    }

    override fun onShowMediaPlayer() {
    }

    override fun onFiltered() {
    }

    override fun onClearFilter() {

    }




}