package ru.mirea.kornilov.sway.domain.repository

import ru.mirea.kornilov.sway.domain.models.SceneType

interface RecognitionRepository {
    fun classifyScene(): SceneType
}
