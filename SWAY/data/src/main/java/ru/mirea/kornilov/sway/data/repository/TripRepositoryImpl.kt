package ru.mirea.kornilov.sway.data.repository

import android.content.Context
import ru.mirea.kornilov.sway.data.db.SwayDatabase
import ru.mirea.kornilov.sway.data.db.TripDao
import ru.mirea.kornilov.sway.data.db.TripEntity
import ru.mirea.kornilov.sway.domain.models.Trip
import ru.mirea.kornilov.sway.domain.repository.TripRepository

class TripRepositoryImpl private constructor(
    private val tripDao: TripDao
) : TripRepository {

    companion object {
        fun create(context: Context): TripRepository {
            return TripRepositoryImpl(SwayDatabase.create(context).tripDao())
        }
    }

    override fun saveWantToVisit(placeId: Int): Boolean {
        val current = tripDao.getByPlaceId(placeId) ?: TripEntity(placeId, false, false, "")
        tripDao.upsert(current.copy(wantToVisit = true))
        return true
    }

    override fun markVisited(placeId: Int): Boolean {
        val current = tripDao.getByPlaceId(placeId) ?: TripEntity(placeId, false, false, "")
        tripDao.upsert(current.copy(visited = true))
        return true
    }

    override fun addNote(placeId: Int, note: String): Boolean {
        val current = tripDao.getByPlaceId(placeId) ?: TripEntity(placeId, false, false, "")
        tripDao.upsert(current.copy(note = note))
        return true
    }

    override fun getTrips(): List<Trip> {
        return tripDao.getAll().map { entity ->
            Trip(entity.placeId, entity.wantToVisit, entity.visited, entity.note)
        }
    }
}
