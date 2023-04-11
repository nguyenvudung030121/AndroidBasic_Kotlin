package com.example.mvp_mediaapplication.song

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvp_mediaapplication.databinding.FragmentSongBinding


class Song_Fragment : Fragment(), SongView {
    lateinit var binding: FragmentSongBinding
    lateinit var songPresenter: SongPresenter
    lateinit var songAdapter: SongAdapter
    var listOfSong:MutableList<Song> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSongBinding.inflate(inflater)
        songPresenter = SongPresenter(this)

        getListOfSong()
        songPresenter.onShowListOfSong()

        return binding.root
    }



    override fun onShowListOfSong() {
        songAdapter = SongAdapter(listOfSong)
        binding.listSong.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding.listSong.adapter = songAdapter
    }
    fun getListOfSong(){

        listOfSong.add(Song(1, "Dĩ Vãng Cuộc Tình", "Thợ hát"))
        listOfSong.add(Song(2, "A", "A"))
        listOfSong.add(Song(3, "B", "B"))
        listOfSong.add(Song(4, "C", "C"))
        listOfSong.add(Song(5, "D", "D"))
        listOfSong.add(Song(6, "E", "E"))
        listOfSong.add(Song(7, "F", "F"))
        listOfSong.add(Song(8, "G", "G"))
        listOfSong.add(Song(9, "H", "H"))
        listOfSong.add(Song(10, "J", "J"))
        listOfSong.add(Song(11, "K", "K"))

    }
    override fun onShowMediaPlayer() {
    }

    override fun onFiltered() {
    }

    override fun onClearFilter() {

    }




}