package me.ijachok.librarykotlin.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.ijachok.librarykotlin.BookRecViewAdapter
import me.ijachok.librarykotlin.R
import me.ijachok.librarykotlin.Supplier
import me.ijachok.librarykotlin.room.BookViewModel

class AlreadyReadBooksActivity : AppCompatActivity() {
    private lateinit var bookViewModel: BookViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_already_read_books)

        val alReadBooksRecView: RecyclerView = findViewById(R.id.alreadyReadBooksRecView)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        alReadBooksRecView.layoutManager = layoutManager

        bookViewModel = ViewModelProvider(this)[BookViewModel::class.java]
        bookViewModel.getListWithBooks(Supplier.ALREADY_READ).observe(this) { books ->
            alReadBooksRecView.adapter = BookRecViewAdapter(this, books.first().books, bookViewModel)
        }
        /*
        alReadBooksRecView.adapter = BookRecViewAdapter(
            this,
            bookViewModel.getListWithBooks(Supplier.ALREADY_READ),
            bookViewModel
        )

         */

    }
}