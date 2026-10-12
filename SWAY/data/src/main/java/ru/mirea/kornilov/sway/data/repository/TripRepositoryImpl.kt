package ru.mirea.kornilov.sway.data.repository

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import ru.mirea.kornilov.sway.data.db.SwayDatabase
import ru.mirea.kornilov.sway.data.db.TripDao
import ru.mirea.kornilov.sway.data.db.TripEntity
import ru.mirea.kornilov.sway.domain.models.Trip
import ru.mirea.kornilov.sway.domain.repository.TripRepository

class TripRepositoryImpl private constructor(
    private val tripDao: TripDao,
    private val userId: String
) : TripRepository {

    companion object {
        fun create(context: Context): TripRepository {
            val userId = FirebaseAuth.getInstance().currentUser?.uid.orEmpty()
            return TripRepositoryImpl(SwayDatabase.create(context).tripDao(), userId)
        }
    }

    override fun saveWantToVisit(placeId: Int): Boolean {
        val current = currentTrip(placeId) ?: return false
        tripDao.upsert(current.copy(wantToVisit = !current.wantToVisit))
        return true
    }

    override fun markVisited(placeId: Int): Boolean {
        val current = currentTrip(placeId) ?: return false
        tripDao.upsert(current.copy(visited = !current.visited))
        return true
    }

    override fun addNote(placeId: Int, note: String): Boolean {
        val current = currentTrip(placeId) ?: return false
        tripDao.upsert(current.copy(note = note))
        return true
    }

    override fun getTrips(): List<Trip> {
        if (userId.isEmpty()) {
            return emptyList()
        }
        return tripDao.getAll(userId).map { entity ->
            Trip(entity.placeId, entity.wantToVisit, entity.visited, entity.note)
        }
    }

    private fun currentTrip(placeId: Int): TripEntity? {
        if (userId.isEmpty()) {
            return null
        }
        return tripDao.getByPlace(userId, placeId) ?: TripEntity(userId, placeId, false, false, "")
    }
}
