package ru.mirea.kornilov.sway.data.db

import androidx.room.Entity

@Entity(tableName = "trips", primaryKeys = ["userId", "placeId"])
data class TripEntity(
    val userId: String,
    val placeId: Int,
    val wantToVisit: Boolean,
    val visited: Boolean,
    val note: String
)
