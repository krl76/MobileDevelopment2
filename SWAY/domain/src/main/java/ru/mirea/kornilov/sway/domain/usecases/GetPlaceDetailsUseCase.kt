package ru.mirea.kornilov.sway.domain.usecases

import ru.mirea.kornilov.sway.domain.models.Place
import ru.mirea.kornilov.sway.domain.repository.PlaceRepository

class GetPlaceDetailsUseCase(
    private val placeRepository: PlaceRepository
) {
    fun execute(placeId: Int): Place? {
        return placeRepository.getPlaceById(placeId)
    }
}
