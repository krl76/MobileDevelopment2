package ru.mirea.kornilov.sway.domain.repository

import ru.mirea.kornilov.sway.domain.models.Place

sealed class PlacesLoadResult {
    data class Success(val places: List<Place>) : PlacesLoadResult()
    data class Failure(val error: PlacesError) : PlacesLoadResult()
}

enum class PlacesError {
    NO_NETWORK,
    BAD_RESPONSE
}
