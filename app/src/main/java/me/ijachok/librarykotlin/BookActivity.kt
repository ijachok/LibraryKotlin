package me.ijachok.librarykotlin

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.google.android.material.imageview.ShapeableImageView

class BookActivity : AppCompatActivity() {
    private lateinit var imageBookCover: ShapeableImageView
    private lateinit var buttonStartReading: MaterialButton
    private lateinit var buttonWantToRead: MaterialButton
    private lateinit var buttonMarkAsRead: MaterialButton
    private lateinit var buttonAddToFavourites: MaterialButton
    private lateinit var textTitleValue: TextView
    private lateinit var textAuthorValue: TextView
    private lateinit var textShDesc: TextView
    private lateinit var textPagesValue: TextView
    private lateinit var textLongDescription: TextView

    private var parent: ConstraintLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)

        imageBookCover = findViewById(R.id.imageBookCover)

        buttonStartReading = findViewById(R.id.buttonStartReading)
        buttonWantToRead = findViewById(R.id.buttonReadLater)
        buttonMarkAsRead = findViewById(R.id.buttonMarkAsRead)
        buttonAddToFavourites = findViewById(R.id.buttonAddToFavourites)

        textTitleValue = findViewById(R.id.textTitleValue)
        textAuthorValue = findViewById(R.id.textAuthorValue)
        textShDesc = findViewById(R.id.textShDesc)
        textPagesValue = findViewById(R.id.textPagesValue)
        textLongDescription = findViewById(R.id.textLongDescription)

        parent = findViewById(R.id.bookConstraintLayout)

        val intent = intent

        if (intent != null) {
            val book = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra(BookRecViewAdapter.BOOK_ID_KEY, Book::class.java)
            }else{
                @Suppress("DEPRECATION")
                intent.getParcelableExtra(BookRecViewAdapter.BOOK_ID_KEY) as? Book
            }
            if (book != null) {
                setBook(book)

                handleFavourites(book)
                handleCurrentlyReading(book)
                handleReadLater(book)
                handleAlreadyRead(book)
            }
        }
    }

    private fun handleFavourites(book: Book) {
        if (Supplier.favouriteBooks.contains(book)) buttonAddToFavourites.toggle()

        buttonAddToFavourites.addOnCheckedChangeListener { button, isChecked ->
            if (isChecked) {
                if (!Supplier.favouriteBooks.add(book)) Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
            } else Supplier.favouriteBooks.remove(book)
        }
    }

    private fun handleCurrentlyReading(book: Book) {
        if (Supplier.currentlyReadingBooks.contains(book)) buttonStartReading.toggle()

        buttonStartReading.addOnCheckedChangeListener { button, isChecked ->
            if (isChecked) {
                if (!Supplier.currentlyReadingBooks.add(book)) Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
            } else Supplier.currentlyReadingBooks.remove(book)
        }
    }

    private fun handleReadLater(book: Book) {
        if (Supplier.readLaterBooks.contains(book)) buttonWantToRead.toggle()

        buttonWantToRead.addOnCheckedChangeListener { button, isChecked ->
            if (isChecked) {
                if (!Supplier.readLaterBooks.add(book)) Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
            } else Supplier.readLaterBooks.remove(book)
        }
    }

    private fun handleAlreadyRead(book: Book) {
        if (Supplier.alreadyReadBooks.contains(book)) buttonMarkAsRead.toggle()

        buttonMarkAsRead.addOnCheckedChangeListener { button, isChecked ->
            if (isChecked) {
                if (!Supplier.alreadyReadBooks.add(book)) Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
            } else Supplier.alreadyReadBooks.remove(book)
        }
    }

    private fun setBook(book: Book) {
        Glide.with(this).asBitmap().load(book.imageURL).into(imageBookCover)
        textTitleValue.text = book.title
        textShDesc.text = book.shortDescription
        textAuthorValue.text = book.author
        textPagesValue.text = book.pages.toString()
        textLongDescription.text = book.longDescription
    }
}