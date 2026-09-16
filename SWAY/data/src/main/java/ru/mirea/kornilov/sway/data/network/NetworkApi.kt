package ru.mirea.kornilov.sway.data.network

import ru.mirea.kornilov.sway.data.network.models.PlaceDto
import ru.mirea.kornilov.sway.data.network.models.WeatherDto

interface NetworkApi {
    fun getPlaces(): List<PlaceDto>
    fun getPlaceById(id: Int): PlaceDto?
    fun getWeather(placeId: Int): WeatherDto
}
