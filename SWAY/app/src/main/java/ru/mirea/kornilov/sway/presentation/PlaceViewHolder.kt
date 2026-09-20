package ru.mirea.kornilov.sway.presentation

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.mirea.kornilov.sway.R
import ru.mirea.kornilov.sway.domain.models.Place
import ru.mirea.kornilov.sway.domain.models.SceneType

class PlaceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val imageView: ImageView = itemView.findViewById(R.id.imagePlace)
    private val nameView: TextView = itemView.findViewById(R.id.textPlaceName)
    private val countryView: TextView = itemView.findViewById(R.id.textPlaceCountry)
    private val sceneView: TextView = itemView.findViewById(R.id.textPlaceScene)

    fun bind(place: Place) {
        nameView.text = place.name
        countryView.text = place.country
        sceneView.setText(sceneLabel(place.sceneType))
        imageView.load(place.imageUrl) {
            crossfade(true)
            placeholder(R.drawable.bg_place_placeholder)
            error(R.drawable.bg_place_placeholder)
        }
    }

    private fun sceneLabel(sceneType: SceneType): Int = when (sceneType) {
        SceneType.MOUNTAINS -> R.string.scene_mountains
        SceneType.SEA -> R.string.scene_sea
        SceneType.CITY -> R.string.scene_city
        SceneType.FOREST -> R.string.scene_forest
    }
}
