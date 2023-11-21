package me.ijachok.librarykotlin.room.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["bookListName"], unique = true)])
data class BookList(
    @PrimaryKey(autoGenerate = true)
     val bookListId: Long,
     val bookListName: String
)