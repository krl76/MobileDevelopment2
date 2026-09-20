package ru.mirea.kornilov.sway.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.mirea.kornilov.sway.R
import ru.mirea.kornilov.sway.domain.models.Place

class PlaceAdapter : RecyclerView.Adapter<PlaceViewHolder>() {
    private var items: List<Place> = emptyList()

    fun setItems(places: List<Place>) {
        items = places
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_place, parent, false)
        return PlaceViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
