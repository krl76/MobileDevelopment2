package ru.mirea.kornilov.sway.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.mirea.kornilov.sway.domain.models.Place
import ru.mirea.kornilov.sway.domain.repository.AuthRepository
import ru.mirea.kornilov.sway.domain.repository.PlaceRepository
import ru.mirea.kornilov.sway.domain.repository.PlacesError
import ru.mirea.kornilov.sway.domain.repository.PlacesLoadResult
import ru.mirea.kornilov.sway.domain.usecases.GetPlacesUseCase
import ru.mirea.kornilov.sway.domain.usecases.GetProfileUseCase

class MainViewModel(
    private val placeRepository: PlaceRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val places = MutableLiveData<List<Place>>(emptyList())
    private val avatarLetter = MutableLiveData("?")
    private val status = MutableLiveData(CatalogStatus.LOADING)

    init {
        Log.d(MainViewModel::class.java.simpleName, "MainViewModel created")
        loadPlaces()
        val login = GetProfileUseCase(authRepository).execute()?.login
        avatarLetter.value = login?.firstOrNull()?.uppercaseChar()?.toString() ?: "?"
    }

    fun getPlaces(): LiveData<List<Place>> = places
    fun getAvatarLetter(): LiveData<String> = avatarLetter
    fun getStatus(): LiveData<CatalogStatus> = status

    fun loadPlaces() {
        status.value = CatalogStatus.LOADING
        GetPlacesUseCase(placeRepository).execute { result ->
            when (result) {
                is PlacesLoadResult.Success -> {
                    places.value = result.places
                    status.value = CatalogStatus.READY
                }
                is PlacesLoadResult.Failure -> {
                    places.value = emptyList()
                    status.value = when (result.error) {
                        PlacesError.NO_NETWORK -> CatalogStatus.NO_NETWORK
                        PlacesError.BAD_RESPONSE -> CatalogStatus.BAD_RESPONSE
                    }
                }
            }
        }
    }

    enum class CatalogStatus {
        LOADING,
        READY,
        NO_NETWORK,
        BAD_RESPONSE
    }

    override fun onCleared() {
        Log.d(MainViewModel::class.java.simpleName, "MainViewModel cleared")
        super.onCleared()
    }
}
