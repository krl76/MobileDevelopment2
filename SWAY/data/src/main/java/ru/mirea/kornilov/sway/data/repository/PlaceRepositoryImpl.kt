package ru.mirea.kornilov.sway.data.repository

import ru.mirea.kornilov.sway.data.network.NetworkApi
import ru.mirea.kornilov.sway.data.network.models.PlaceDto
import ru.mirea.kornilov.sway.domain.models.Place
import ru.mirea.kornilov.sway.domain.models.SceneType
import ru.mirea.kornilov.sway.domain.repository.PlaceRepository

class PlaceRepositoryImpl(
    private val networkApi: NetworkApi
) : PlaceRepository {

    override fun getPlaces(): List<Place> {
        return networkApi.getPlaces().map { it.toDomain() }
    }

    override fun getPlaceById(id: Int): Place? {
        return networkApi.getPlaceById(id)?.toDomain()
    }

    override fun getPlacesByScene(sceneType: SceneType): List<Place> {
        return getPlaces().filter { it.sceneType == sceneType }
    }

    private fun PlaceDto.toDomain(): Place {
        return Place(
            id = id,
            name = name,
            country = country,
            description = description,
            imageUrl = imageUrl,
            sceneType = runCatching { SceneType.valueOf(sceneType) }.getOrDefault(SceneType.CITY)
        )
    }
}
