package com.example.roomdatabase_example_2nd.room

import androidx.room.*
import androidx.room.OnConflictStrategy.Companion.REPLACE

@Dao
interface NoteDao {
    @Insert(onConflict = REPLACE)
    fun insert(data:NoteModel)

    @Update
    fun update(data: NoteModel):Int

    @Delete
    fun delete(data: NoteModel)

    @Query("SELECT * from note ORDER BY id ASC")
    fun getAll(): List<NoteModel>

    @Query("SELECT * FROM note WHERE id = :id LIMIT 1")
    fun getNote(id: String): NoteModel

    @Query("DELETE FROM note")
    fun deleteAll():Int


}