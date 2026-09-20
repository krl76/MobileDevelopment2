package ru.mirea.kornilov.recyclerviewapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val imageView: ImageView = itemView.findViewById(R.id.imageView)
    private val titleView: TextView = itemView.findViewById(R.id.textViewTitle)
    private val descriptionView: TextView = itemView.findViewById(R.id.textViewDescription)

    fun bind(event: HistoryEvent) {
        imageView.setImageResource(event.imageRes)
        titleView.text = event.title
        descriptionView.text = event.description
    }
}
