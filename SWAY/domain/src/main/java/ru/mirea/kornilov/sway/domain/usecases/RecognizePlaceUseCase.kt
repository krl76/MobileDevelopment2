package ru.mirea.kornilov.sway.domain.usecases

import ru.mirea.kornilov.sway.domain.models.Place
import ru.mirea.kornilov.sway.domain.models.SceneType
import ru.mirea.kornilov.sway.domain.repository.PlaceRepository
import ru.mirea.kornilov.sway.domain.repository.RecognitionRepository

class RecognizePlaceUseCase(
    private val recognitionRepository: RecognitionRepository,
    private val placeRepository: PlaceRepository
) {
    fun execute(): Pair<SceneType, List<Place>> {
        val scene = recognitionRepository.classifyScene()
        return scene to placeRepository.getPlacesByScene(scene)
    }
}
