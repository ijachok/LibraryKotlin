package me.ijachok.librarykotlin.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBook(book: Book)
    @Update
    fun updateBook(book: Book)
    @Delete
    fun deleteBook(book: Book)
    @Query(value = "SELECT * FROM book_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Book>>
}