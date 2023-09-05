package me.ijachok.librarykotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.color.DynamicColors


class MainActivity : AppCompatActivity() {

    private lateinit var btnAllBooks: Button
    private lateinit var btnFavourites: Button
    private lateinit var btnCurReading: Button
    private lateinit var btnReadLater: Button
    private lateinit var btnAlRead: Button
    private lateinit var btnAbout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DynamicColors.applyToActivitiesIfAvailable(this.application)

        btnAllBooks = findViewById(R.id.btnAllBooks)
        btnFavourites = findViewById(R.id.btnFavourites)
        btnCurReading = findViewById(R.id.btnCurReading)
        btnReadLater = findViewById(R.id.btnReadLater)
        btnAlRead = findViewById(R.id.btnAlRead)
        btnAbout = findViewById(R.id.btnAbout)

        btnAllBooks.setOnClickListener {
            startActivity(Intent(this, AllBooksActivity::class.java))
        }

        btnFavourites.setOnClickListener {
            startActivity(Intent(this, FavouriteBooksActivity::class.java))
        }

        btnCurReading.setOnClickListener {
            startActivity(Intent(this, CurrentlyReadingBooksActivity::class.java))
        }

        btnReadLater.setOnClickListener {
            startActivity(Intent(this, ReadLaterBooksActivity::class.java))
        }

        btnAlRead.setOnClickListener {
            startActivity(Intent(this, AlreadyReadBooksActivity::class.java))
        }

        btnAbout.setOnClickListener {
            // TODO: about page
        }

    }
}