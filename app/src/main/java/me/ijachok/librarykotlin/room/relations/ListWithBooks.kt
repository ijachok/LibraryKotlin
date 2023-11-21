package me.ijachok.librarykotlin.room.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import me.ijachok.librarykotlin.room.entities.Book
import me.ijachok.librarykotlin.room.entities.BookList

data class ListWithBooks(
    @Embedded val bookList: BookList,
    @Relation(
        parentColumn = "bookListName",
        entityColumn = "bookId",
        associateBy = Junction(BookListCrossRef::class)
    )
    val books: List<Book>
)