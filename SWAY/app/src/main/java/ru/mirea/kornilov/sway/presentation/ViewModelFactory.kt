package ru.mirea.kornilov.sway.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.mirea.kornilov.sway.data.network.MockNetworkApi
import ru.mirea.kornilov.sway.data.network.NetworkApi
import ru.mirea.kornilov.sway.data.repository.AuthRepositoryImpl
import ru.mirea.kornilov.sway.data.repository.PlaceRepositoryImpl
import ru.mirea.kornilov.sway.data.storage.sharedprefs.SharedPrefClientStorage
import ru.mirea.kornilov.sway.domain.repository.AuthRepository
import ru.mirea.kornilov.sway.domain.repository.PlaceRepository

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
            return MainViewModel(placeRepository, authRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: $modelClass")
    }
}
