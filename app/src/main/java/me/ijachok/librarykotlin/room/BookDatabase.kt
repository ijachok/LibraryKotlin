package me.ijachok.librarykotlin.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import me.ijachok.librarykotlin.room.entities.Book
import me.ijachok.librarykotlin.room.entities.BookList
import me.ijachok.librarykotlin.room.relations.BookListCrossRef

@Database(
    entities = [Book::class, BookList::class, BookListCrossRef::class],
    version = 1,
    exportSchema = false
)
abstract class BookDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao

    companion object {
        @Volatile
        private var INSTANCE: BookDatabase? = null

        fun getDatabase(context: Context): BookDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BookDatabase::class.java,
                    "book_database"

                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}