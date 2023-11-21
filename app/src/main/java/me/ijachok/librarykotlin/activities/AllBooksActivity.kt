package me.ijachok.librarykotlin.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import me.ijachok.librarykotlin.BookRecViewAdapter
import me.ijachok.librarykotlin.R
import me.ijachok.librarykotlin.room.BookDao
import me.ijachok.librarykotlin.room.BookViewModel

class AllBooksActivity : AppCompatActivity() {
    private lateinit var bookViewModel: BookViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_books)

        val allBooksRecView: RecyclerView = findViewById(R.id.allBooksRecView)
        val fbAdd: FloatingActionButton = findViewById(R.id.fbAdd)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        allBooksRecView.layoutManager = layoutManager



        bookViewModel = ViewModelProvider(this)[BookViewModel::class.java]
        bookViewModel.allBooks.observe(this) { books ->
            allBooksRecView.adapter = BookRecViewAdapter(this, books, bookViewModel)
        }

        fbAdd.setOnClickListener {
            startActivity(Intent(this, AddBookActivity::class.java))
        }

    }
}