package ru.mirea.kornilov.sway.domain.repository

import ru.mirea.kornilov.sway.domain.models.Trip

interface TripRepository {
    fun saveWantToVisit(placeId: Int): Boolean
    fun markVisited(placeId: Int): Boolean
    fun addNote(placeId: Int, note: String): Boolean
    fun getTrips(): List<Trip>
}
