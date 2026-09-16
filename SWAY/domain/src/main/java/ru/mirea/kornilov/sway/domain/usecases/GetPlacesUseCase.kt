package ru.mirea.kornilov.sway.domain.usecases

import ru.mirea.kornilov.sway.domain.models.Place
import ru.mirea.kornilov.sway.domain.repository.PlaceRepository

class GetPlacesUseCase(
    private val placeRepository: PlaceRepository
) {
    fun execute(): List<Place> {
        return placeRepository.getPlaces()
    }
}
