package me.ijachok.librarykotlin

import android.os.Parcel
import android.os.Parcelable

data class Book(
    var title: String?,
    var author: String?,
    var imageURL: String?,
    var pages: Int,
    var shortDescription: String?,
    var longDescription: String?
) : Parcelable {
    object StaticHolder {
        var count = 0
    }

    val id = ++StaticHolder.count
    var isExpanded = false

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    ) {
        isExpanded = parcel.readByte() != 0.toByte()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(author)
        parcel.writeString(imageURL)
        parcel.writeInt(pages)
        parcel.writeString(shortDescription)
        parcel.writeString(longDescription)
        parcel.writeByte(if (isExpanded) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel): Book {
            return Book(parcel)
        }

        override fun newArray(size: Int): Array<Book?> {
            return arrayOfNulls(size)
        }
    }
}

object Supplier {

    var allBooks = mutableListOf(
        Book(
            "Zero Bugs",
            "Kate Thompson",
            "https://m.media-amazon.com/images/I/61DbOXo7NlL._SX404_BO1,204,203,200_.jpg",
            182,
            "and Program Faster",
            "A book about programming, improving skill, and avoiding mistakes. The author spent two years researching every bug avoidance technique she could find."
        ),
        Book(
            "The Pragmatic Programmer",
            "Andrew Hunt",
            "https://m.media-amazon.com/images/I/51IA4hT6jrL._SX380_BO1,204,203,200_.jpg",
            352,
            "Your Journey To Mastery",
            "Written as a series of self-contained sections and filled with classic and fresh anecdotes, thoughtful examples, and interesting analogies, The Pragmatic Programmer illustrates the best approaches and major pitfalls of many different aspects of software development."
        ),
        Book(
            "Clean Code",
            "Robert C. Martin, Dean Wampler",
            "https://m.media-amazon.com/images/I/61lU253WNnL._SX373_BO1,204,203,200_.jpg",
            464,
            "A Handbook of Agile Software Craftsmanship",
            "Noted software expert Robert C. Martin, presents a revolutionary paradigm with Clean Code: A Handbook of Agile Software Craftsmanship."
        ),
        Book(
            "Head First Design Patterns",
            "Bert Bates, Eric Freeman",
            "https://m.media-amazon.com/images/I/61APhXCksuL._SX430_BO1,204,203,200_.jpg",
            692,
            "A Brain-Friendly Guide",
            "At any given moment, someone struggles with the same software design problems you have. And, chances are, someone else has already solved your problem."
        ),
        Book(
            "Refactoring",
            "Kent Beck, Martin Fowler",
            "https://m.media-amazon.com/images/I/51k+BvsOl2L._SX392_BO1,204,203,200_.jpg",
            431,
            "Improving the Design of Existing Code",
            "Refactoring is about improving the design of existing code. It is the process of changing a software system in such a way that it does not alter the external behavior of the code, yet improves its internal structure."
        ),
        Book(
            "The Effective Engineer",
            "Edmond Lau",
            "https://m.media-amazon.com/images/I/41p7Ty0Hx9L._SX331_BO1,204,203,200_.jpg",
            260,
            "How to Leverage Your Efforts In Software Engineering to Make a Disproportionate and Meaningful Impact",
            "Introducing The Effective Engineer — the only book designed specifically for today's software engineers, based on extensive interviews with engineering leaders at top tech companies, and packed with hundreds of techniques to accelerate your career."
        ),
        Book(
            "The Clean Coder",
            "Robert C. Martin",
            "https://m.media-amazon.com/images/I/51SxkB3f-qL._SX381_BO1,204,203,200_.jpg",
            256,
            "A Code of Conduct for Professional Programmers",
            "In The Clean Coder: A Code of Conduct for Professional Programmers, legendary software expert Robert C. Martin introduces the disciplines, techniques, tools, and practices of true software craftsmanship."
        ),
        Book(
            "Working Effectively with Legacy Code",
            "Michael C. Feathers",
            "https://m.media-amazon.com/images/I/51yS8PYs03L._SX376_BO1,204,203,200_.jpg",
            464,
            "",
            "This book provides programmers with the ability to cost effectively handle common legacy code problems without having to go through the hugely expensive task of rewriting all existing code. "
        ),
        Book(
            "Agile Software Development",
            "Robert C. Martin",
            "https://m.media-amazon.com/images/I/51GXv-sMBYL._SX379_BO1,204,203,200_.jpg",
            552,
            ", Principles, Patterns, and Practices",
            "Written by a software developer for software developers, this book is a unique collection of the latest software development methods. The author includes OOD, UML, Design Patterns, Agile and XP methods with a detailed description of a complete software design for reusable programs in C++ and Java."
        )
    )
    val favouriteBooks = mutableListOf<Book>()
    val currentlyReadingBooks = mutableListOf<Book>()
    val readLaterBooks = mutableListOf<Book>()
    val alreadyReadBooks = mutableListOf<Book>()
}

enum class ListType {
    ALL, FAVOURITE, CURRENTLY_READING, READ_LATER, ALREADY_READ
}
