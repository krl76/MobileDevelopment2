package ru.mirea.kornilov.sway.domain.models

data class Weather(
    val placeId: Int,
    val temperatureC: Int,
    val description: String
)
