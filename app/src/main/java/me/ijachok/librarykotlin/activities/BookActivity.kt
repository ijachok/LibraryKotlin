package me.ijachok.librarykotlin.activities

import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.google.android.material.imageview.ShapeableImageView
import me.ijachok.librarykotlin.BookRecViewAdapter
import me.ijachok.librarykotlin.R
import me.ijachok.librarykotlin.Supplier
import me.ijachok.librarykotlin.room.entities.Book
import me.ijachok.librarykotlin.room.BookViewModel

class BookActivity : AppCompatActivity() {
    private lateinit var imageBookCover: ShapeableImageView
    private lateinit var buttonStartReading: MaterialButton
    private lateinit var buttonWantToRead: MaterialButton
    private lateinit var buttonMarkAsRead: MaterialButton
    private lateinit var buttonAddToFavourites: MaterialButton
    private lateinit var buttonDelete: MaterialButton
    private lateinit var textTitleValue: TextView
    private lateinit var textAuthorValue: TextView
    private lateinit var textShDesc: TextView
    private lateinit var textPagesValue: TextView
    private lateinit var textLongDescription: TextView

    private var parent: ConstraintLayout? = null

    private lateinit var bookViewModel: BookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)


        imageBookCover = findViewById(R.id.imageBookCover)

        buttonStartReading = findViewById(R.id.buttonStartReading)
        buttonWantToRead = findViewById(R.id.buttonReadLater)
        buttonMarkAsRead = findViewById(R.id.buttonMarkAsRead)
        buttonAddToFavourites = findViewById(R.id.buttonAddToFavourites)
        buttonDelete = findViewById(R.id.buttonDelete)

        textTitleValue = findViewById(R.id.textTitleValue)
        textAuthorValue = findViewById(R.id.textAuthorValue)
        textShDesc = findViewById(R.id.textShDesc)
        textPagesValue = findViewById(R.id.textPagesValue)
        textLongDescription = findViewById(R.id.textLongDescription)

        parent = findViewById(R.id.bookConstraintLayout)

        bookViewModel = ViewModelProvider(this)[BookViewModel::class.java]

        val intent = intent

        if (intent != null) {
            val book = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra(BookRecViewAdapter.BOOK_ID_KEY, Book::class.java)
            } else {
                @Suppress("DEPRECATION")
                intent.getParcelableExtra(BookRecViewAdapter.BOOK_ID_KEY) as? Book
            }
            if (book != null) {
                title = book.title
                setBook(book)

                handleFavourites(book)
                handleCurrentlyReading(book)
                handleReadLater(book)
                handleAlreadyRead(book)
                handleDelete(book)
            }
        }
    }


    private fun handleFavourites(book: Book) {
        bookViewModel.isBookInList(book.bookId, Supplier.FAVOURITES).observe(this) {
            if (it) buttonAddToFavourites.toggle()
        }

        buttonAddToFavourites.addOnCheckedChangeListener { button, isChecked ->
            if (isChecked)  bookViewModel.addBookToList(book, Supplier.FAVOURITES)
             else bookViewModel.deleteBookFromList(book, Supplier.FAVOURITES)

        }
    }

    private fun handleCurrentlyReading(book: Book) {
        bookViewModel.isBookInList(book.bookId, Supplier.CURRENTLY_READING).observe(this) {
            if (it) buttonStartReading.toggle()
        }

        buttonStartReading.addOnCheckedChangeListener { button, isChecked ->
            if (isChecked) {
                bookViewModel.addBookToList(book, Supplier.CURRENTLY_READING)
            } else bookViewModel.deleteBookFromList(book, Supplier.CURRENTLY_READING)
        }
    }

    private fun handleReadLater(book: Book) {
        bookViewModel.isBookInList(book.bookId, Supplier.READ_LATER).observe(this) {
            if (it) buttonWantToRead.toggle()
        }

        buttonWantToRead.addOnCheckedChangeListener { button, isChecked ->
            if (isChecked) {
                bookViewModel.addBookToList(book, Supplier.READ_LATER)
            } else bookViewModel.deleteBookFromList(book, Supplier.READ_LATER)
        }
    }

    private fun handleAlreadyRead(book: Book) {
        bookViewModel.isBookInList(book.bookId, Supplier.ALREADY_READ).observe(this) {
            if (it) buttonMarkAsRead.toggle()
        }

        buttonMarkAsRead.addOnCheckedChangeListener { button, isChecked ->
            if (isChecked) {
                bookViewModel.addBookToList(book, Supplier.ALREADY_READ)
            } else bookViewModel.deleteBookFromList(book, Supplier.ALREADY_READ)
        }
    }

    private fun handleDelete(book: Book) {
        buttonDelete.setOnClickListener {
            buttonDelete.toggle()

            val builder = AlertDialog.Builder(this)
            builder.setPositiveButton("Yes") { _, _ ->
                bookViewModel.deleteBook(book)
                // TODO: delete from all lists
                Toast.makeText(
                    this,
                    "Removed ${book.title}",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
            builder.setNegativeButton("No") { _, _ ->
            }
            builder.setTitle("Delete book")
            builder.setMessage("Are you sure you want to delete ${book.title} from all the lists?")
            builder.create().show()

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