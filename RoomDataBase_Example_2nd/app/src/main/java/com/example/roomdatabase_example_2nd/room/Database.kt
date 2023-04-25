package com.example.roomdatabase_example_2nd.room

import android.content.Context
import android.provider.ContactsContract.Data
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase

@androidx.room.Database(entities = [NoteModel::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun noteDao(): NoteDao


    companion object {
        private var INSTANCE: Database? = null

        fun getInstance(context: Context): Database {
            if (INSTANCE == null) {
                synchronized(Database::class){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        Database::class.java,
                        "NoteDatabase").build()
                }
            }
            return INSTANCE!!
        }

        fun destroyInstance(){
            INSTANCE = null
        }

    }

}