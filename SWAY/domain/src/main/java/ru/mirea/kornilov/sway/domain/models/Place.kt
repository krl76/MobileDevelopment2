package ru.mirea.kornilov.sway.domain.models

data class Place(
    val id: Int,
    val name: String,
    val country: String,
    val description: String,
    val imageUrl: String,
    val sceneType: SceneType
)
