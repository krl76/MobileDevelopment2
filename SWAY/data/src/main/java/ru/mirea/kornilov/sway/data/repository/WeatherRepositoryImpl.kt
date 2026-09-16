package ru.mirea.kornilov.sway.data.repository

import ru.mirea.kornilov.sway.data.network.NetworkApi
import ru.mirea.kornilov.sway.domain.models.Weather
import ru.mirea.kornilov.sway.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val networkApi: NetworkApi
) : WeatherRepository {
    override fun getWeather(placeId: Int): Weather {
        val dto = networkApi.getWeather(placeId)
        return Weather(dto.placeId, dto.temperatureC, dto.description)
    }
}
