package ru.mirea.kornilov.sway.data.network.models

data class PlaceDto(
    val id: Int,
    val name: String,
    val country: String,
    val description: String,
    val imageUrl: String,
    val sceneType: String
)
