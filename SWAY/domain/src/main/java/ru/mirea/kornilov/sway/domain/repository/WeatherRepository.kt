package ru.mirea.kornilov.sway.domain.repository

import ru.mirea.kornilov.sway.domain.models.Weather

interface WeatherRepository {
    fun getWeather(placeId: Int): Weather
}
