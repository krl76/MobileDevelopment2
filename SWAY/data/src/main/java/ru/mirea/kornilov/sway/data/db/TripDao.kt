package ru.mirea.kornilov.sway.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TripDao {
    @Query("SELECT * FROM trips WHERE userId = :userId")
    fun getAll(userId: String): List<TripEntity>

    @Query("SELECT * FROM trips WHERE userId = :userId AND placeId = :placeId LIMIT 1")
    fun getByPlace(userId: String, placeId: Int): TripEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(entity: TripEntity)
}
