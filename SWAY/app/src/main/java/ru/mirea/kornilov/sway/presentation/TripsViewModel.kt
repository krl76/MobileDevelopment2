package ru.mirea.kornilov.sway.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.mirea.kornilov.sway.domain.models.Trip
import ru.mirea.kornilov.sway.domain.repository.TripRepository
import ru.mirea.kornilov.sway.domain.usecases.GetMyTripsUseCase

class TripsViewModel(
    private val tripRepository: TripRepository
) : ViewModel() {

    private val trips = MutableLiveData<List<Trip>>(emptyList())

    fun getTrips(): LiveData<List<Trip>> = trips

    fun reload() {
        trips.value = GetMyTripsUseCase(tripRepository).execute()
    }
}
