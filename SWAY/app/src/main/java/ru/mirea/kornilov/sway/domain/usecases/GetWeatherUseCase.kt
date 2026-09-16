package ru.mirea.kornilov.sway.domain.usecases

import ru.mirea.kornilov.sway.domain.models.Weather
import ru.mirea.kornilov.sway.domain.repository.WeatherRepository

class GetWeatherUseCase(
    private val weatherRepository: WeatherRepository
) {
    fun execute(placeId: Int): Weather {
        return weatherRepository.getWeather(placeId)
    }
}
