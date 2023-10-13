package me.ijachok.librarykotlin.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "book_table")
data class Book(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var title: String?,
    var author: String?,
    var imageURL: String?,
    var pages: Int,
    var shortDescription: String?,
    var longDescription: String?
) : Parcelable