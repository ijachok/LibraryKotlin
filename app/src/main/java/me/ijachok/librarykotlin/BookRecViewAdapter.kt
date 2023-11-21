package me.ijachok.librarykotlin

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionManager
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.imageview.ShapeableImageView
import me.ijachok.librarykotlin.activities.BookActivity
import me.ijachok.librarykotlin.room.BookViewModel
import me.ijachok.librarykotlin.room.entities.Book
import me.ijachok.librarykotlin.room.relations.BookListCrossRef

class BookRecViewAdapter(
    private val context: Context,
    private val books: List<Book>,
    private val model: BookViewModel
) :
    RecyclerView.Adapter<BookRecViewAdapter.ViewHolder>() {


    private var isFavouriteBooksActivity: Boolean = false

    constructor(
        context: Context,
        books: List<Book>,
        model: BookViewModel,
        isFavouriteBooksActivity: Boolean
    ) : this(
        context,
        books,
        model
    ) {
        this.isFavouriteBooksActivity = isFavouriteBooksActivity
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.book_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtTitle.text = books[position].title
        holder.txtAuthor.text = books[position].author
        holder.txtShDescription.text = books[position].shortDescription
        Glide.with(context).asBitmap().load(books[position].imageURL).into(holder.bookCoverImage)

        model.isBookInList(books[position].bookId, Supplier.FAVOURITES)
            .observe(holder.itemView.context as LifecycleOwner) {
                if (it) holder.btnToggleFav.toggle()
            }

        holder.btnToggleFav.addOnCheckedChangeListener { _: MaterialButton, isChecked: Boolean ->
            if (isChecked) {
                model.addBookToList(
                    books[position], Supplier.FAVOURITES
                )
            } else {
                model.deleteBookFromList(books[position], Supplier.FAVOURITES)
                if (isFavouriteBooksActivity) {
                    notifyItemRemoved(position)
                }
            }
        }

        holder.parent.setOnClickListener {
            val intent = Intent(context, BookActivity::class.java)
            intent.putExtra(BOOK_ID_KEY, books[position])
            context.startActivity(intent)
        }

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val parent: MaterialCardView = itemView.findViewById(R.id.bookCardView)
        val bookCoverImage: ShapeableImageView = itemView.findViewById(R.id.bookCoverImage)
        val txtTitle: TextView = itemView.findViewById(R.id.txtTitle)
        val txtShDescription: TextView = itemView.findViewById(R.id.txtShDescription)
        val txtAuthor: TextView = itemView.findViewById(R.id.txtAuthor)
        val btnToggleFav: MaterialButton = itemView.findViewById(R.id.btnToggleFav)

    }

    companion object {
        const val BOOK_ID_KEY: String = "bookID"
    }
}
