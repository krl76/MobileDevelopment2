package ru.mirea.kornilov.sway.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trips")
data class TripEntity(
    @PrimaryKey val placeId: Int,
    val wantToVisit: Boolean,
    val visited: Boolean,
    val note: String
)
