package me.ijachok.librarykotlin.room

import android.util.Log
import androidx.lifecycle.LiveData
import me.ijachok.librarykotlin.room.entities.Book
import me.ijachok.librarykotlin.room.entities.BookList
import me.ijachok.librarykotlin.room.relations.BookListCrossRef
import me.ijachok.librarykotlin.room.relations.BookWithLists
import me.ijachok.librarykotlin.room.relations.ListWithBooks

class BookRepository(private val bookDao: BookDao) {

    val getAllBooks: LiveData<List<Book>> = bookDao.getAllBooks()

    val getAllBookLists: LiveData<List<BookList>> = bookDao.getAllBookLists()
    fun addBook(book: Book) {
        bookDao.addBook(book)
    }

    fun addBookList(bookList: BookList) {
        bookDao.addBookList(bookList)
    }

    fun addBookToList(book: Book, bookListName: String) {
        synchronized(this){
            bookDao.addBookListCrossRef(BookListCrossRef(book.bookId, bookListName))
        }

    }


    fun updateBook(book: Book) {
        bookDao.updateBook(book)
    }

    fun updateBookList(bookList: BookList) {
        bookDao.updateBookList(bookList)
    }

    fun deleteBook(book: Book) {
        bookDao.deleteBook(book)
    }

    fun deleteBookFromList(book: Book, bookListName: String){
        synchronized(this){
            bookDao.deleteBookListCrossRef(BookListCrossRef(book.bookId, bookListName))
        }

    }

    fun isBookInList(bookId: Long, bookListName: String): Boolean {
        return bookDao.getCrossRef(bookId, bookListName) != null

    }

    fun getBookWithLists(book: Book): LiveData<List<BookWithLists>> {
        return bookDao.getBookWithLists(book.bookId)
    }

    fun getListWithBooks(bookListName: String): LiveData<List<ListWithBooks>> {
        return bookDao.getListWithBooks(bookListName)
    }


}