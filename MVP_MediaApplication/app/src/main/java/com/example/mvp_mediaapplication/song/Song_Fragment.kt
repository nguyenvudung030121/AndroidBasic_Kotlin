package com.example.mvp_mediaapplication.song

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvp_mediaapplication.databinding.FragmentSongBinding


class Song_Fragment : Fragment() {
    lateinit var binding: FragmentSongBinding
    var listOfSong: MutableList<Song> = mutableListOf()
    lateinit var songAdapter: SongAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSongBinding.inflate(inflater)

        getListOfSong()
        setListOfSong()

        return binding.root
    }

    fun setListOfSong() {
        songAdapter = SongAdapter(listOfSong)
        binding.listSong.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding.listSong.adapter = songAdapter
    }

    private fun getListOfSong() {
        listOfSong.add(Song(1, "Dĩ Vãng Cuộc Tình", "Thợ hát"))
        listOfSong.add(Song(2, "Dĩ Vãng Cuộc Tình", "Thợ hát"))
        listOfSong.add(Song(3, "Dĩ Vãng Cuộc Tình", "Thợ hát"))
        listOfSong.add(Song(4, "Dĩ Vãng Cuộc Tình", "Thợ hát"))
        listOfSong.add(Song(5, "Dĩ Vãng Cuộc Tình", "Thợ hát"))
        listOfSong.add(Song(6, "Dĩ Vãng Cuộc Tình", "Thợ hát"))
        listOfSong.add(Song(7, "Dĩ Vãng Cuộc Tình", "Thợ hát"))
        listOfSong.add(Song(8, "Dĩ Vãng Cuộc Tình", "Thợ hát"))
        listOfSong.add(Song(9, "Dĩ Vãng Cuộc Tình", "Thợ hát"))
        listOfSong.add(Song(10, "Dĩ Vãng Cuộc Tình", "Thợ hát"))
        listOfSong.add(Song(11, "Dĩ Vãng Cuộc Tình", "Thợ hát"))
    }


}