package ru.mirea.kornilov.sway.domain.usecases

import ru.mirea.kornilov.sway.domain.models.Trip
import ru.mirea.kornilov.sway.domain.repository.TripRepository

class GetMyTripsUseCase(
    private val tripRepository: TripRepository
) {
    fun execute(): List<Trip> {
        return tripRepository.getTrips()
    }
}
