package ru.mirea.kornilov.sway.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.mirea.kornilov.sway.data.network.MockNetworkApi
import ru.mirea.kornilov.sway.data.network.PlaceApi
import ru.mirea.kornilov.sway.data.repository.AuthRepositoryImpl
import ru.mirea.kornilov.sway.data.repository.PlaceRepositoryImpl
import ru.mirea.kornilov.sway.data.repository.RecognitionRepositoryImpl
import ru.mirea.kornilov.sway.data.repository.TripRepositoryImpl
import ru.mirea.kornilov.sway.data.repository.WeatherRepositoryImpl
import ru.mirea.kornilov.sway.data.storage.sharedprefs.SharedPrefClientStorage
import ru.mirea.kornilov.sway.domain.repository.AuthRepository
import ru.mirea.kornilov.sway.domain.repository.PlaceRepository

class ViewModelFactory(
    context: Context,
    private val placeId: Int = 0
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
            val placeRepository: PlaceRepository = PlaceRepositoryImpl(PlaceApi.create())
            return MainViewModel(
                placeRepository,
                authRepository,
                RecognitionRepositoryImpl()
            ) as T
        }
        if (modelClass.isAssignableFrom(TripsViewModel::class.java)) {
            return TripsViewModel(TripRepositoryImpl.create(context)) as T
        }
        if (modelClass.isAssignableFrom(PlaceViewModel::class.java)) {
            return PlaceViewModel(
                placeId,
                WeatherRepositoryImpl(MockNetworkApi()),
                TripRepositoryImpl.create(context)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: $modelClass")
    }
}
