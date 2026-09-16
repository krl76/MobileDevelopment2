package ru.mirea.kornilov.sway.data.repository

import ru.mirea.kornilov.sway.domain.models.Trip
import ru.mirea.kornilov.sway.domain.repository.TripRepository

class TripRepositoryImpl : TripRepository {
    private val trips = mutableMapOf<Int, Trip>()

    override fun saveWantToVisit(placeId: Int): Boolean {
        val current = trips[placeId] ?: Trip(placeId, wantToVisit = false, visited = false, note = "")
        trips[placeId] = current.copy(wantToVisit = true)
        return true
    }

    override fun markVisited(placeId: Int): Boolean {
        val current = trips[placeId] ?: Trip(placeId, wantToVisit = false, visited = false, note = "")
        trips[placeId] = current.copy(visited = true)
        return true
    }

    override fun addNote(placeId: Int, note: String): Boolean {
        val current = trips[placeId] ?: Trip(placeId, wantToVisit = false, visited = false, note = "")
        trips[placeId] = current.copy(note = note)
        return true
    }

    override fun getTrips(): List<Trip> = trips.values.toList()
}
