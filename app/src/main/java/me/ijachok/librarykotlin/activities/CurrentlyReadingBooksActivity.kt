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

class CurrentlyReadingBooksActivity : AppCompatActivity() {
    private lateinit var bookViewModel:BookViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currently_reading_books)

        val curReadingBooksRecView: RecyclerView = findViewById(R.id.currentlyReadingBooksRecView)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        curReadingBooksRecView.layoutManager = layoutManager

        bookViewModel = ViewModelProvider(this)[BookViewModel::class.java]

        bookViewModel.getListWithBooks(Supplier.CURRENTLY_READING).observe(this) { books ->
            curReadingBooksRecView.adapter = BookRecViewAdapter(this, books.first().books, bookViewModel)
        }
        /*
        curReadingBooksRecView.adapter = BookRecViewAdapter(
            this,
            bookViewModel.getListWithBooks(Supplier.CURRENTLY_READING),
            bookViewModel
        )

         */
    }
}