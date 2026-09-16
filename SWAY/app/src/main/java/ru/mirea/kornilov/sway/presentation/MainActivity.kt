package ru.mirea.kornilov.sway.presentation

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ru.mirea.kornilov.sway.R
import ru.mirea.kornilov.sway.data.repository.AuthRepositoryImpl
import ru.mirea.kornilov.sway.data.repository.PlaceRepositoryImpl
import ru.mirea.kornilov.sway.data.repository.RecognitionRepositoryImpl
import ru.mirea.kornilov.sway.data.repository.TripRepositoryImpl
import ru.mirea.kornilov.sway.data.repository.WeatherRepositoryImpl
import ru.mirea.kornilov.sway.domain.repository.AuthRepository
import ru.mirea.kornilov.sway.domain.repository.PlaceRepository
import ru.mirea.kornilov.sway.domain.repository.RecognitionRepository
import ru.mirea.kornilov.sway.domain.repository.TripRepository
import ru.mirea.kornilov.sway.domain.repository.WeatherRepository
import ru.mirea.kornilov.sway.domain.usecases.GetPlacesUseCase
import ru.mirea.kornilov.sway.domain.usecases.GetWeatherUseCase
import ru.mirea.kornilov.sway.domain.usecases.RecognizePlaceUseCase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textViewStub)

        val placeRepository: PlaceRepository = PlaceRepositoryImpl()
        val weatherRepository: WeatherRepository = WeatherRepositoryImpl()
        val tripRepository: TripRepository = TripRepositoryImpl()
        val authRepository: AuthRepository = AuthRepositoryImpl()
        val recognitionRepository: RecognitionRepository = RecognitionRepositoryImpl()

        findViewById<View>(R.id.buttonLoadStub).setOnClickListener {
            val places = GetPlacesUseCase(placeRepository).execute()
            val weather = GetWeatherUseCase(weatherRepository).execute(places.first().id)
            val (scene, matched) = RecognizePlaceUseCase(
                recognitionRepository,
                placeRepository
            ).execute()
            textView.text = buildString {
                append("Места: ")
                append(places.joinToString { it.name })
                append("\nПогода: ${weather.temperatureC}°C, ${weather.description}")
                append("\nРаспознавание: $scene → ${matched.joinToString { it.name }}")
                append("\nСессия: ${authRepository.getCurrentUser()}")
                append("\nПоездки: ${tripRepository.getTrips().size}")
            }
        }
    }
}
