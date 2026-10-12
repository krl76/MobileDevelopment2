package ru.mirea.kornilov.sway.data.repository

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.mirea.kornilov.sway.data.network.PlaceApi
import ru.mirea.kornilov.sway.data.network.models.PlaceDto
import ru.mirea.kornilov.sway.domain.models.Place
import ru.mirea.kornilov.sway.domain.models.SceneType
import ru.mirea.kornilov.sway.domain.repository.PlaceRepository
import ru.mirea.kornilov.sway.domain.repository.PlacesError
import ru.mirea.kornilov.sway.domain.repository.PlacesLoadResult

class PlaceRepositoryImpl(
    private val placeApi: PlaceApi
) : PlaceRepository {

    private val cache = mutableListOf<Place>()

    override fun loadPlaces(onResult: (PlacesLoadResult) -> Unit) {
        placeApi.getPlaces().enqueue(object : Callback<List<PlaceDto>> {
            override fun onResponse(
                call: Call<List<PlaceDto>>,
                response: Response<List<PlaceDto>>
            ) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    val places = body.map { it.toDomain() }
                    cache.clear()
                    cache.addAll(places)
                    onResult(PlacesLoadResult.Success(places))
                } else {
                    onResult(PlacesLoadResult.Failure(PlacesError.BAD_RESPONSE))
                }
            }

            override fun onFailure(call: Call<List<PlaceDto>>, t: Throwable) {
                if (call.isCanceled) return
                onResult(PlacesLoadResult.Failure(PlacesError.NO_NETWORK))
            }
        })
    }

    override fun getPlaceById(id: Int): Place? = cache.find { it.id == id }

    override fun getPlacesByScene(sceneType: SceneType): List<Place> {
        return cache.filter { it.sceneType == sceneType }
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
