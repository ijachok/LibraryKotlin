package me.ijachok.librarykotlin.room

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.ijachok.librarykotlin.room.entities.Book
import me.ijachok.librarykotlin.room.entities.BookList
import me.ijachok.librarykotlin.room.relations.BookWithLists
import me.ijachok.librarykotlin.room.relations.ListWithBooks

class BookViewModel(application: Application) : AndroidViewModel(application) {

    val allBooks: LiveData<List<Book>>
    val allBookLists: LiveData<List<BookList>>

    private val repository: BookRepository

    init {
        val bookDao = BookDatabase.getDatabase(application).bookDao()
        repository = BookRepository(bookDao)
        allBooks = repository.getAllBooks
        allBookLists = repository.getAllBookLists
    }

    fun addBook(book: Book) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addBook(book)
        }
    }

    fun addBookList(bookList: BookList) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addBookList(bookList)
        }
    }

    fun addBookToList(book: Book, bookListName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addBookToList(book, bookListName)
            Log.d("addBookToList", "added: ${book.title}, $bookListName")
        }
    }

    fun updateBook(book: Book) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateBook(book)
        }
    }

    fun updateBookList(bookList: BookList) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateBookList(bookList)
        }
    }

    fun deleteBook(book: Book) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteBook(book)
        }
    }

    fun deleteBookFromList(book: Book, bookListName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteBookFromList(book, bookListName)
            Log.d("deleteBookFromList", "removed ${book.title}, $bookListName")
        }
    }

     fun getBookWithLists(book: Book): LiveData<List<BookWithLists>> {
        return repository.getBookWithLists(book)
    }

     fun getListWithBooks(bookListName: String): LiveData<List<ListWithBooks>> {
        return repository.getListWithBooks(bookListName)

    }

    fun isBookInList(bookId: Long, bookListName: String): LiveData<Boolean> {
        val isBookInListLiveData = MutableLiveData<Boolean>()
        viewModelScope.launch(Dispatchers.IO) {
            val isBookInList = repository.isBookInList(bookId, bookListName)
            isBookInListLiveData.postValue(isBookInList)
        }
        return isBookInListLiveData
    }
}