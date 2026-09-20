package ru.mirea.kornilov.sway.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.mirea.kornilov.sway.domain.models.Place
import ru.mirea.kornilov.sway.domain.models.SceneType
import ru.mirea.kornilov.sway.domain.models.Trip
import ru.mirea.kornilov.sway.domain.models.Weather
import ru.mirea.kornilov.sway.domain.repository.AuthRepository
import ru.mirea.kornilov.sway.domain.repository.PlaceRepository
import ru.mirea.kornilov.sway.domain.repository.RecognitionRepository
import ru.mirea.kornilov.sway.domain.repository.TripRepository
import ru.mirea.kornilov.sway.domain.repository.WeatherRepository
import ru.mirea.kornilov.sway.domain.usecases.GetMyTripsUseCase
import ru.mirea.kornilov.sway.domain.usecases.GetPlacesUseCase
import ru.mirea.kornilov.sway.domain.usecases.GetProfileUseCase
import ru.mirea.kornilov.sway.domain.usecases.GetWeatherUseCase
import ru.mirea.kornilov.sway.domain.usecases.RecognizePlaceUseCase

class MainViewModel(
    private val authRepository: AuthRepository,
    private val placeRepository: PlaceRepository,
    private val weatherRepository: WeatherRepository,
    private val tripRepository: TripRepository,
    private val recognitionRepository: RecognitionRepository
) : ViewModel() {

    private val networkLive = MutableLiveData<NetworkSnapshot>()
    private val tripsLive = MutableLiveData<List<Trip>>()
    private val summary = MediatorLiveData<String>()

    init {
        Log.d(MainViewModel::class.java.simpleName, "MainViewModel created")
        summary.addSource(networkLive) { merge() }
        summary.addSource(tripsLive) { merge() }
    }

    fun getSummary(): LiveData<String> = summary

    fun loadStub() {
        val user = GetProfileUseCase(authRepository).execute()
        val places = GetPlacesUseCase(placeRepository).execute()
        val weather = places.firstOrNull()?.let { place ->
            GetWeatherUseCase(weatherRepository).execute(place.id)
        }
        val (scene, matched) = RecognizePlaceUseCase(
            recognitionRepository,
            placeRepository
        ).execute()
        networkLive.value = NetworkSnapshot(
            login = user?.login,
            places = places,
            weather = weather,
            scene = scene,
            matched = matched
        )
        tripsLive.value = GetMyTripsUseCase(tripRepository).execute()
    }

    private fun merge() {
        val network = networkLive.value ?: return
        val trips = tripsLive.value ?: return
        val weatherLine = network.weather?.let { weather ->
            "${weather.temperatureC}°C, ${weather.description}"
        } ?: "нет данных"
        summary.value = buildString {
            append("Клиент (SharedPreferences / Firebase): ${network.login}")
            append("\nМеста (NetworkApi): ${network.places.joinToString { it.name }}")
            append("\nПогода (NetworkApi): $weatherLine")
            append("\nПоездки (Room): ${trips.size}")
            append("\nРаспознавание: ${network.scene} → ${network.matched.joinToString { it.name }}")
        }
    }

    override fun onCleared() {
        Log.d(MainViewModel::class.java.simpleName, "MainViewModel cleared")
        super.onCleared()
    }

    private data class NetworkSnapshot(
        val login: String?,
        val places: List<Place>,
        val weather: Weather?,
        val scene: SceneType,
        val matched: List<Place>
    )
}
