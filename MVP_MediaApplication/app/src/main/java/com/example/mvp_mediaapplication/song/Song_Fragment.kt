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
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSource
import com.google.android.exoplayer2.util.MimeTypes


class Song_Fragment : Fragment(), SongView {
    lateinit var binding: FragmentSongBinding
    lateinit var songPresenter: SongPresenter
    lateinit var songAdapter: SongAdapter
    var listOfSong:MutableList<Song>? = mutableListOf()
    var mediaPlayer: MediaPlayer? = null

    private var player: ExoPlayer? = null
    private val isPlaying get() = player?.isPlaying ?: false

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.playerView.apply {
            showController()
            initializePlayer()
        }
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


    private fun initializePlayer() {
        player = activity?.let {
            ExoPlayer.Builder(it.applicationContext) // <- context
                .build()
        }

        // create a media item.
        val mediaItem = MediaItem.Builder()
            .setUri("https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4")
            .setMimeType(MimeTypes.AUDIO_UNKNOWN)
            .build()

        // Create a media source and pass the media item
        val mediaSource = activity?.let { DefaultDataSource.Factory(it.applicationContext) }?.let {
            ProgressiveMediaSource.Factory(
                it // <- context
            )
                .createMediaSource(mediaItem)
        }

        // Finally assign this media source to the player
        player!!.apply {
            if (mediaSource != null) {
                setMediaSource(mediaSource)
            }
            playWhenReady = false // start playing when the exoplayer has setup
            seekTo(0, 0L) // Start from the beginning
            prepare() // Change the state from idle.
        }.also {
            // Do not forget to attach the player to the view
            binding.playerView.player = it
        }
    }




}