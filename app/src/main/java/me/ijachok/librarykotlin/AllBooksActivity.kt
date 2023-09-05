package me.ijachok.librarykotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AllBooksActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_books)

        val allBooksRecView: RecyclerView = findViewById(R.id.allBooksRecView)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        allBooksRecView.layoutManager = layoutManager

        allBooksRecView.adapter = BookRecViewAdapter(this, Supplier.allBooks)
    }
}