package me.ijachok.librarykotlin.room.relations

import androidx.room.Entity
@Entity(primaryKeys = ["bookId", "bookListName"])
data class BookListCrossRef(
    val bookId:Long,
    val bookListName:String
)