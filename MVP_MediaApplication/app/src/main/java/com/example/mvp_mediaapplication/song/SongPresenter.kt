package com.example.mvp_mediaapplication.song

import android.app.Activity
import android.app.Application
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import com.example.mvp_mediaapplication.main.MainActivity


class SongPresenter(var songView: SongView) {

    fun onShowListOfSong() {
        songView.onShowListOfSong()
    }

    fun getMusicList(context: Context): MutableList<Song>? {
        val musicList = mutableListOf<Song>()

        // Tạo URI để truy cập danh sách các file nhạc
        val musicUri: Uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI

        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.DATA
        )
        val musicCursor: Cursor? =
            context.contentResolver.query(musicUri, projection, null, null, null)

        // Xử lý từng file nhạc trong danh sách
        if (musicCursor != null && musicCursor.moveToFirst()) {
            val idColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media._ID)
            val titleColumn: Int = musicCursor.getColumnIndex(MediaStore.Audio.Media.TITLE)
            val artistColumn: Int = musicCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)
            val pathColumn  = musicCursor.getColumnIndex(MediaStore.Audio.Media.DATA)

            do {
                val id =  musicCursor.getString(idColumn)
                val title: String = musicCursor.getString(titleColumn)
                val artist: String = musicCursor.getString(artistColumn)
                val path = musicCursor.getString(pathColumn)

                // Thêm tên bài hát và tên nghệ sĩ vào danh sách
                musicList.add(Song("$id","$title","$artist","$path"))
            } while (musicCursor.moveToNext())
        }

        // Đóng con trỏ để giải phóng tài nguyên
        if (musicCursor != null) {
            musicCursor.close()
        }
        return musicList
    }


}