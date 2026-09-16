package ru.mirea.kornilov.sway.domain.usecases

import ru.mirea.kornilov.sway.domain.repository.TripRepository

class SaveWantToVisitUseCase(
    private val tripRepository: TripRepository
) {
    fun execute(placeId: Int): Boolean {
        return tripRepository.saveWantToVisit(placeId)
    }
}
