package ru.mirea.kornilov.listviewapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class BookAdapter(
    context: Context,
    books: List<Book>
) : ArrayAdapter<Book>(context, 0, books) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_book, parent, false)
        val holder = view.tag as? ViewHolder ?: ViewHolder(view).also { view.tag = it }
        val book = getItem(position) ?: return view
        holder.title.text = book.title
        holder.author.text = book.author
        return view
    }

    private class ViewHolder(view: View) {
        val title: TextView = view.findViewById(R.id.textTitle)
        val author: TextView = view.findViewById(R.id.textAuthor)
    }
}
