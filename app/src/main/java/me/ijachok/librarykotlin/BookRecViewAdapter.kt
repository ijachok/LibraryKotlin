package me.ijachok.librarykotlin

import android.content.Context
import android.content.Intent
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.imageview.ShapeableImageView

class BookRecViewAdapter(private val context: Context, private val books: List<Book>) :
    RecyclerView.Adapter<BookRecViewAdapter.ViewHolder>() {


    private var isFavouriteBooksActivity : Boolean = false

    constructor(context: Context, books: List<Book>, isFavouriteBooksActivity: Boolean) : this(context, books){
        this.isFavouriteBooksActivity = isFavouriteBooksActivity
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.book_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtTitle.text = books[position].title
        holder.txtAuthor.text = books[position].author
        holder.txtShDescription.text = books[position].shortDescription
        Glide.with(context).asBitmap().load(books[position].imageURL).into(holder.bookCoverImage)

        if (Supplier.favouriteBooks.contains(books[position])) {
            holder.btnToggleFav.toggle()
        }

        holder.btnToggleFav.addOnCheckedChangeListener { button: MaterialButton, isChecked: Boolean ->
            if (isChecked) {
                if (!Supplier.favouriteBooks.add(books[position])) {
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
                }
            } else {
                Supplier.favouriteBooks.remove(books[position])
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

        if (books[holder.adapterPosition].isExpanded) {
            TransitionManager.beginDelayedTransition(holder.parent)
            holder.expandedRelLayout.visibility = View.VISIBLE
            holder.downArrow.rotation = 180f
            holder.parent.elevation = 10f
        } else {
            TransitionManager.beginDelayedTransition(holder.parent)
            holder.expandedRelLayout.visibility = View.GONE
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val parent: MaterialCardView = itemView.findViewById(R.id.bookCardView)
        val bookCoverImage: ShapeableImageView = itemView.findViewById(R.id.bookCoverImage)
        val txtTitle: TextView = itemView.findViewById(R.id.txtTitle)
        val txtShDescription: TextView = itemView.findViewById(R.id.txtShDescription)
        val txtAuthor: TextView = itemView.findViewById(R.id.txtAuthor)
        val downArrow: ImageView = itemView.findViewById(R.id.downArrow)
        val expandedRelLayout: RelativeLayout = itemView.findViewById(R.id.expandedRelLayout)
        val btnToggleFav: MaterialButton = itemView.findViewById(R.id.btnToggleFav)

        init {
            downArrow.setOnClickListener {
                val book = books[adapterPosition]
                book.isExpanded = !book.isExpanded
                notifyItemChanged(adapterPosition)
            }
        }

    }

    companion object {
        const val BOOK_ID_KEY: String = "bookID"
    }
}
