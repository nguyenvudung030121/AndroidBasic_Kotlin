package com.example.mvp_mediaapplication.song

import android.media.AudioAttributes
import android.media.MediaPlayer
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
    var mediaPlayer: MediaPlayer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSongBinding.inflate(inflater)

        songPresenter = SongPresenter(this)
        listOfSong = activity?.let { songPresenter.getMusicList(it.applicationContext) }
        songPresenter.onShowListOfSong()

        return binding.root
    }

    override fun onShowListOfSong() {
        songAdapter = listOfSong?.let { SongAdapter(it, object : SongAdapter.OnSongClickListener {
            override fun onPlay(song: Song) {
                val url = song.path
                mediaPlayer?.stop()
                mediaPlayer?.release()
                mediaPlayer = MediaPlayer().apply {
                    setAudioAttributes(
                        AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .build()
                    )
                    setDataSource(url)
                    prepareAsync()
                    setOnPreparedListener { mp -> mp?.start() };
                }
            }
        }) }!!

        binding.listSong.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

        binding.listSong.adapter = songAdapter


    }


    override fun onShowMediaPlayer() {
        binding.CardMediaPlayer.visibility = View.VISIBLE
    }

    override fun onFiltered() {
    }

    override fun onClearFilter() {

    }




}