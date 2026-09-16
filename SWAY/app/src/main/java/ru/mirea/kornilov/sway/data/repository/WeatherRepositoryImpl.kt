package ru.mirea.kornilov.sway.data.repository

import ru.mirea.kornilov.sway.domain.models.Weather
import ru.mirea.kornilov.sway.domain.repository.WeatherRepository

class WeatherRepositoryImpl : WeatherRepository {
    private val weatherByPlace = mapOf(
        1 to Weather(1, 8, "облачно, ветер"),
        2 to Weather(2, 24, "ясно"),
        3 to Weather(3, 16, "небольшой дождь"),
        4 to Weather(4, 12, "туман"),
        5 to Weather(5, 21, "переменная облачность")
    )

    override fun getWeather(placeId: Int): Weather {
        return weatherByPlace[placeId] ?: Weather(placeId, 18, "нет данных")
    }
}
