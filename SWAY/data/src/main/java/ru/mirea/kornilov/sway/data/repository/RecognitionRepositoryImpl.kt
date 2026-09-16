package ru.mirea.kornilov.sway.data.repository

import ru.mirea.kornilov.sway.domain.models.SceneType
import ru.mirea.kornilov.sway.domain.repository.RecognitionRepository

class RecognitionRepositoryImpl : RecognitionRepository {
    override fun classifyScene(): SceneType {
        return SceneType.MOUNTAINS
    }
}
