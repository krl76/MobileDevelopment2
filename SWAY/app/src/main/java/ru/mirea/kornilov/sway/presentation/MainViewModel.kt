package ru.mirea.kornilov.sway.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.mirea.kornilov.sway.domain.models.Place
import ru.mirea.kornilov.sway.domain.repository.AuthRepository
import ru.mirea.kornilov.sway.domain.repository.PlaceRepository
import ru.mirea.kornilov.sway.domain.usecases.GetPlacesUseCase
import ru.mirea.kornilov.sway.domain.usecases.GetProfileUseCase

class MainViewModel(
    private val placeRepository: PlaceRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val places = MutableLiveData<List<Place>>()
    private val avatarLetter = MutableLiveData("?")

    init {
        Log.d(MainViewModel::class.java.simpleName, "MainViewModel created")
        loadPlaces()
        val login = GetProfileUseCase(authRepository).execute()?.login
        avatarLetter.value = login?.firstOrNull()?.uppercaseChar()?.toString() ?: "?"
    }

    fun getPlaces(): LiveData<List<Place>> = places
    fun getAvatarLetter(): LiveData<String> = avatarLetter

    fun loadPlaces() {
        places.value = GetPlacesUseCase(placeRepository).execute()
    }

    override fun onCleared() {
        Log.d(MainViewModel::class.java.simpleName, "MainViewModel cleared")
        super.onCleared()
    }
}
