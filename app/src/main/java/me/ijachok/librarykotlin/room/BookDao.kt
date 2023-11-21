package me.ijachok.librarykotlin.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import me.ijachok.librarykotlin.room.entities.Book
import me.ijachok.librarykotlin.room.entities.BookList
import me.ijachok.librarykotlin.room.relations.BookListCrossRef
import me.ijachok.librarykotlin.room.relations.BookWithLists
import me.ijachok.librarykotlin.room.relations.ListWithBooks

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBook(book: Book)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBookList(bookList: BookList)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addBookListCrossRef(crossRef: BookListCrossRef)

    @Update
    fun updateBook(book: Book)

    @Update
    fun updateBookList(bookList: BookList)

    @Delete
    fun deleteBook(book: Book)

    @Delete
    fun deleteBookListCrossRef(crossRef: BookListCrossRef)

    @Transaction
    @Query(value = "SELECT * FROM Book ORDER BY bookId ASC")
    fun getAllBooks(): LiveData<List<Book>>

    @Transaction
    @Query(value = "SELECT * FROM BookList ORDER BY bookListId ASC")
    fun getAllBookLists(): LiveData<List<BookList>>

    @Transaction
    @Query("SELECT * FROM BookListCrossRef WHERE bookId = :bookId AND bookListName = :bookListName")
    fun getCrossRef(bookId: Long, bookListName: String): BookListCrossRef?

    @Transaction
    @Query("SELECT * FROM Book WHERE bookId = :bookId")
    fun getBookWithLists(bookId: Long): LiveData<List<BookWithLists>>

    @Transaction
    @Query("SELECT * FROM BookList WHERE bookListName = :bookListName")
    fun getListWithBooks(bookListName: String): LiveData<List<ListWithBooks>>

}