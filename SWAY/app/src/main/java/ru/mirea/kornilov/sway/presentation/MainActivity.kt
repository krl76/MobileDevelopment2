package ru.mirea.kornilov.sway.presentation

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ru.mirea.kornilov.sway.R
import ru.mirea.kornilov.sway.data.network.MockNetworkApi
import ru.mirea.kornilov.sway.data.network.NetworkApi
import ru.mirea.kornilov.sway.data.repository.AuthRepositoryImpl
import ru.mirea.kornilov.sway.data.repository.PlaceRepositoryImpl
import ru.mirea.kornilov.sway.data.repository.RecognitionRepositoryImpl
import ru.mirea.kornilov.sway.data.repository.TripRepositoryImpl
import ru.mirea.kornilov.sway.data.repository.WeatherRepositoryImpl
import ru.mirea.kornilov.sway.data.storage.sharedprefs.SharedPrefClientStorage
import ru.mirea.kornilov.sway.domain.repository.AuthRepository
import ru.mirea.kornilov.sway.domain.repository.PlaceRepository
import ru.mirea.kornilov.sway.domain.repository.RecognitionRepository
import ru.mirea.kornilov.sway.domain.repository.TripRepository
import ru.mirea.kornilov.sway.domain.repository.WeatherRepository
import ru.mirea.kornilov.sway.domain.usecases.GetPlacesUseCase
import ru.mirea.kornilov.sway.domain.usecases.GetProfileUseCase
import ru.mirea.kornilov.sway.domain.usecases.GetWeatherUseCase
import ru.mirea.kornilov.sway.domain.usecases.RecognizePlaceUseCase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textViewStub)

        val clientStorage = SharedPrefClientStorage(this)
        val networkApi: NetworkApi = MockNetworkApi()

        val placeRepository: PlaceRepository = PlaceRepositoryImpl(networkApi)
        val weatherRepository: WeatherRepository = WeatherRepositoryImpl(networkApi)
        val tripRepository: TripRepository = TripRepositoryImpl.create(this)
        val authRepository: AuthRepository = AuthRepositoryImpl(clientStorage)
        val recognitionRepository: RecognitionRepository = RecognitionRepositoryImpl()

        findViewById<View>(R.id.buttonLoadStub).setOnClickListener {
            val user = GetProfileUseCase(authRepository).execute()
            val places = GetPlacesUseCase(placeRepository).execute()
            val weather = GetWeatherUseCase(weatherRepository).execute(places.first().id)
            val (scene, matched) = RecognizePlaceUseCase(
                recognitionRepository,
                placeRepository
            ).execute()
            textView.text = buildString {
                append("Клиент (SharedPreferences): ${clientStorage.getLogin()}")
                append("\nПрофиль (Firebase): ${user?.login}")
                append("\nМеста (NetworkApi): ${places.joinToString { it.name }}")
                append("\nПогода (NetworkApi): ${weather.temperatureC}°C, ${weather.description}")
                append("\nПоездки (Room): ${tripRepository.getTrips().size}")
                append("\nРаспознавание: $scene → ${matched.joinToString { it.name }}")
            }
        }
    }
}
