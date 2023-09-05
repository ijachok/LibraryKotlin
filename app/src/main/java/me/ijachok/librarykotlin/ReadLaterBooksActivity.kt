package me.ijachok.librarykotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ReadLaterBooksActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_later_books)

        val allBooksRecView: RecyclerView = findViewById(R.id.readLaterBooksRecView)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        allBooksRecView.layoutManager = layoutManager

        allBooksRecView.adapter = BookRecViewAdapter(this, Supplier.readLaterBooks)
    }
}