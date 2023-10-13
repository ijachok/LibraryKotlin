package me.ijachok.librarykotlin

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import me.ijachok.librarykotlin.room.Book
import me.ijachok.librarykotlin.room.BookViewModel

class AddBookActivity : AppCompatActivity() {

    lateinit var editTextImageLink: TextInputEditText
    lateinit var editTextTitle: TextInputEditText
    lateinit var editTextTitleLayout: TextInputLayout
    lateinit var editTextShDesc: TextInputEditText
    lateinit var editTextAuthor: TextInputEditText
    lateinit var editTextPages: TextInputEditText
    lateinit var editTextLongDesc: TextInputEditText
    lateinit var btnAddBook: Button
    private lateinit var bookViewModel: BookViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)

        bookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)
        editTextImageLink = findViewById(R.id.editTextImageLink)
        editTextTitle = findViewById(R.id.editTextTitle)
        editTextTitleLayout = findViewById(R.id.editTextTitleLayout)
        editTextShDesc = findViewById(R.id.editTextShDesc)
        editTextAuthor = findViewById(R.id.editTextAuthor)
        editTextPages = findViewById(R.id.editTextPages)
        editTextLongDesc = findViewById(R.id.editTextLongDesc)
        btnAddBook = findViewById(R.id.btnAddBook)

        editTextImageLink.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (TextUtils.isEmpty(editTextImageLink.text)) {
                    editTextImageLink.error = "Can`t be empty"
                }
            }
        })

        editTextTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (TextUtils.isEmpty(editTextTitle.text)) {
                    editTextTitle.error = "Can`t be empty"
                }
            }
        })

        editTextShDesc.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (TextUtils.isEmpty(editTextShDesc.text)) {
                    editTextShDesc.error = "Can`t be empty"
                }
            }
        })

        editTextAuthor.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (TextUtils.isEmpty(editTextAuthor.text)) {
                    editTextAuthor.error = "Can`t be empty"
                }
            }
        })

        editTextPages.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (TextUtils.isEmpty(editTextPages.text)) {
                    editTextPages.error = "Can`t be empty"
                }
            }
        })

        btnAddBook.setOnClickListener {
            if (input()) addBook()
        }

    }

    private fun addBook() {
        val title = editTextTitle.text.toString()
        val shDesk = editTextShDesc.text.toString()
        val author = editTextAuthor.text.toString()
        val pages = editTextPages.text.toString().toInt()
        val longDesc = editTextLongDesc.text.toString()
        val imageURL = editTextImageLink.text.toString()

        bookViewModel.addBook(Book(0, title, author, imageURL, pages, shDesk, longDesc))

    }

    private fun input(): Boolean{
        return !TextUtils.isEmpty(editTextImageLink.text) && !TextUtils.isEmpty(editTextTitle.text) && !TextUtils.isEmpty(editTextShDesc.text) && !TextUtils.isEmpty(editTextAuthor.text) && !TextUtils.isEmpty(editTextPages.text)
    }
}