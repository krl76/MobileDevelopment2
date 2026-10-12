package ru.mirea.kornilov.sway.domain.usecases

import ru.mirea.kornilov.sway.domain.repository.TripRepository

class AddPlaceNoteUseCase(
    private val tripRepository: TripRepository
) {
    fun execute(placeId: Int, note: String): Boolean {
        return tripRepository.addNote(placeId, note.trim())
    }
}
