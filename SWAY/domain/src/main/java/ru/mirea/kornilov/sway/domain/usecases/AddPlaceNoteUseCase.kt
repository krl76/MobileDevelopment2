package ru.mirea.kornilov.sway.domain.usecases

import ru.mirea.kornilov.sway.domain.repository.TripRepository

class AddPlaceNoteUseCase(
    private val tripRepository: TripRepository
) {
    fun execute(placeId: Int, note: String): Boolean {
        if (note.isBlank()) {
            return false
        }
        return tripRepository.addNote(placeId, note.trim())
    }
}
