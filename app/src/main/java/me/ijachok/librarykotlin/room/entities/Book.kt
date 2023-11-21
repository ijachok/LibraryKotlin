package me.ijachok.librarykotlin.room.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Book(
    @PrimaryKey(autoGenerate = true)
    val bookId: Long,
    var title: String?,
    var author: String?,
    var imageURL: String?,
    var pages: Int,
    var shortDescription: String?,
    var longDescription: String?
):Parcelable {
    var isExpanded: Boolean = false
}