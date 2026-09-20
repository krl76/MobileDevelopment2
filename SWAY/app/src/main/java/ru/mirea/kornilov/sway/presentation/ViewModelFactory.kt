package ru.mirea.kornilov.sway.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
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

class ViewModelFactory(
    context: Context
) : ViewModelProvider.Factory {
    private val context = context.applicationContext

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val clientStorage = SharedPrefClientStorage(context)
        val authRepository: AuthRepository = AuthRepositoryImpl(clientStorage)

        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(authRepository) as T
        }
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            val networkApi: NetworkApi = MockNetworkApi()
            val placeRepository: PlaceRepository = PlaceRepositoryImpl(networkApi)
            val weatherRepository: WeatherRepository = WeatherRepositoryImpl(networkApi)
            val tripRepository: TripRepository = TripRepositoryImpl.create(context)
            val recognitionRepository: RecognitionRepository = RecognitionRepositoryImpl()
            return MainViewModel(
                authRepository,
                placeRepository,
                weatherRepository,
                tripRepository,
                recognitionRepository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: $modelClass")
    }
}
