package ru.mirea.kornilov.sway.domain.usecases

import ru.mirea.kornilov.sway.domain.repository.PlaceRepository
import ru.mirea.kornilov.sway.domain.repository.PlacesLoadResult

class GetPlacesUseCase(
    private val placeRepository: PlaceRepository
) {
    fun execute(onResult: (PlacesLoadResult) -> Unit) {
        placeRepository.loadPlaces(onResult)
    }
}
