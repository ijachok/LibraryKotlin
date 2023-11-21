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

class FavouriteBooksActivity : AppCompatActivity() {
    private lateinit var bookViewModel: BookViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourite_books)

        val favBooksRecView: RecyclerView = findViewById(R.id.favouriteBooksRecView)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        favBooksRecView.layoutManager = layoutManager

        bookViewModel = ViewModelProvider(this)[BookViewModel::class.java]



        bookViewModel.getListWithBooks(Supplier.FAVOURITES).observe(this) { books ->
            favBooksRecView.adapter = BookRecViewAdapter(this, books.first().books, bookViewModel)
        }

        /*
        favBooksRecView.adapter = BookRecViewAdapter(
            this,
            bookViewModel.getListWithBooks(Supplier.FAVOURITES),
            bookViewModel,
            true
        )
         */
    }
}