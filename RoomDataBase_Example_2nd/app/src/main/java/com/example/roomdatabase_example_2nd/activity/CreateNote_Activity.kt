package com.example.roomdatabase_example_2nd.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.roomdatabase_example_2nd.R
import com.example.roomdatabase_example_2nd.databinding.ActivityCreateNoteBinding
import com.example.roomdatabase_example_2nd.room.Database
import com.example.roomdatabase_example_2nd.room.NoteModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class CreateNote_Activity : AppCompatActivity() {

    lateinit var binding:ActivityCreateNoteBinding
    lateinit var myDb: Database
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_create_note)

        myDb = Database.getInstance(this)

    }

    fun onDone(view: View){
        if (binding.edtTitle.text.isEmpty()) {
            binding.edtTitle.error = "field is required"
            binding.edtTitle.requestFocus()
            return
        }

        val note = NoteModel() //create new note
        note.title =  binding.edtTitle.text.toString()
        note.description =  binding.edtTitle.text.toString()

        //insertData

        CompositeDisposable().add(Observable.fromCallable {
            myDb.noteDao().insert(note) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("respons", "data inserted")
                Toast.makeText(this, "Data inserted", Toast.LENGTH_SHORT).show()
                onBackPressed()
            }
        )
    }

    fun onBack(view:View){
        onBackPressed()
    }
}