package ru.mirea.kornilov.sway.data.network.models

data class WeatherDto(
    val placeId: Int,
    val temperatureC: Int,
    val description: String
)
