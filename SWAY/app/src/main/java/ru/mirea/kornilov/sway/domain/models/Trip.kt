package ru.mirea.kornilov.sway.domain.models

data class Trip(
    val placeId: Int,
    val wantToVisit: Boolean,
    val visited: Boolean,
    val note: String
)
