package ru.mirea.kornilov.sway.domain.repository

import ru.mirea.kornilov.sway.domain.models.Place
import ru.mirea.kornilov.sway.domain.models.SceneType

interface PlaceRepository {
    fun loadPlaces(onResult: (PlacesLoadResult) -> Unit)
    fun getPlaceById(id: Int): Place?
    fun getPlacesByScene(sceneType: SceneType): List<Place>
}
