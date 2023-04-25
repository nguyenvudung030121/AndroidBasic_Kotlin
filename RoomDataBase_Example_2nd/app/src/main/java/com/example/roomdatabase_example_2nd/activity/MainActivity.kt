package com.example.roomdatabase_example_2nd.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.roomdatabase_example_2nd.NoteAdapter
import com.example.roomdatabase_example_2nd.R
import com.example.roomdatabase_example_2nd.databinding.ActivityMainBinding
import com.example.roomdatabase_example_2nd.room.Database
import com.example.roomdatabase_example_2nd.room.NoteModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

/*
    - Impletement những thư viện cần thiết
    - Tạo 1 Model entity
    - Tạo Dao cho entity
    - Tạo abstract class database để đưa entity vào
    - Trong một abstract class database tạo 1 abstract
    - Tạo instance cho database (phải được khai báo là static):
        - Vì nó chỉ cần tạo 1 lần duy nhất tránh lãng phí bộ nhớ
        - Để tạo nó thì cần 1 biến INstance check xem nó đc tạo hay chưa
        - Rồi kiểm tra xem biến INSTANCE đó có null hay không
        - Nếu không thì Đặt khởi tạo instance trong một synchronlize (đồng bộ 1 luồng duy nhất)

    - Tại Main:
        - Khởi tạo instance cho database

*/

class MainActivity : AppCompatActivity() {

    lateinit var myDB: Database
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        myDB = Database.getInstance(this)
//        displayNote()
    }

    fun onCreateNote(view: View) {
        binding.btnCreate.setOnClickListener {
            startActivity(Intent(this, CreateNote_Activity::class.java))
        }
    }

    private fun displayNote() {
        val listNote = myDB.noteDao().getAll()

        /*CompositeDisposable().add(Observable.fromIterable(listNote)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("respons", "data inserted")
                Toast.makeText(this, "Data inserted", Toast.LENGTH_SHORT).show()
                onBackPressed()
            }
        )*/
/*        val list = listOf("1", "2", "3", "4", "5")

        disposable.add(Observable.fromIterable(list)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result: String -> println("Result: $result") },  // xử lý kết quả thành công
                { error: Throwable -> println("Error: " + error.message) })
        )*/

        binding.rvNote.layoutManager = StaggeredGridLayoutManager(
            2,
            StaggeredGridLayoutManager.VERTICAL
        )
        binding.rvNote.adapter = NoteAdapter(listNote, object : NoteAdapter.Listeners {
            override fun onClicked(note: NoteModel) {
                val intent = Intent(this@MainActivity, NoteDetail_Activity::class.java)
                intent.putExtra("extra", "" + note.id)
                startActivity(intent)
            }
        })
    }

    fun setUpAdapter(){

    }

    override fun onResume() {
        super.onResume()
//        displayNote()
    }

}