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

class ReadLaterBooksActivity : AppCompatActivity() {
    private lateinit var bookViewModel: BookViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_later_books)

        val readLaterBooksRecView: RecyclerView = findViewById(R.id.readLaterBooksRecView)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        readLaterBooksRecView.layoutManager = layoutManager

        bookViewModel = ViewModelProvider(this)[BookViewModel::class.java]
        bookViewModel.getListWithBooks(Supplier.READ_LATER).observe(this) { books ->
            readLaterBooksRecView.adapter = BookRecViewAdapter(this, books.first().books, bookViewModel)
        }
        /*
        readLaterBooksRecView.adapter = BookRecViewAdapter(
            this,
            bookViewModel.getListWithBooks(Supplier.READ_LATER),
            bookViewModel
        )

         */
    }
}