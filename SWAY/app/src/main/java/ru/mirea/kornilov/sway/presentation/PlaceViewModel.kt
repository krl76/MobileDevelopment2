package ru.mirea.kornilov.sway.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.mirea.kornilov.sway.domain.models.Trip
import ru.mirea.kornilov.sway.domain.models.Weather
import ru.mirea.kornilov.sway.domain.repository.TripRepository
import ru.mirea.kornilov.sway.domain.repository.WeatherRepository
import ru.mirea.kornilov.sway.domain.usecases.AddPlaceNoteUseCase
import ru.mirea.kornilov.sway.domain.usecases.GetMyTripsUseCase
import ru.mirea.kornilov.sway.domain.usecases.GetWeatherUseCase
import ru.mirea.kornilov.sway.domain.usecases.MarkVisitedUseCase
import ru.mirea.kornilov.sway.domain.usecases.SaveWantToVisitUseCase

class PlaceViewModel(
    private val placeId: Int,
    private val weatherRepository: WeatherRepository,
    private val tripRepository: TripRepository
) : ViewModel() {

    private val weather = MutableLiveData<Weather>()
    private val trip = MutableLiveData<Trip?>()

    init {
        weather.value = GetWeatherUseCase(weatherRepository).execute(placeId)
        reloadTrip()
    }

    fun getWeather(): LiveData<Weather> = weather
    fun getTrip(): LiveData<Trip?> = trip

    fun saveWantToVisit() {
        SaveWantToVisitUseCase(tripRepository).execute(placeId)
        reloadTrip()
    }

    fun markVisited() {
        MarkVisitedUseCase(tripRepository).execute(placeId)
        reloadTrip()
    }

    fun saveNote(note: String): Boolean {
        val saved = AddPlaceNoteUseCase(tripRepository).execute(placeId, note)
        if (saved) {
            reloadTrip()
        }
        return saved
    }

    private fun reloadTrip() {
        trip.value = GetMyTripsUseCase(tripRepository).execute()
            .find { it.placeId == placeId }
    }
}
