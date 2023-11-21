package me.ijachok.librarykotlin.room.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import me.ijachok.librarykotlin.room.entities.Book
import me.ijachok.librarykotlin.room.entities.BookList

data class BookWithLists(
    @Embedded val book: Book,
    @Relation(
        parentColumn = "bookId",
        entityColumn = "bookListName",
        associateBy = Junction(BookListCrossRef::class)
    )
    val bookLists: List<BookList>
)