package ru.mirea.kornilov.sway.data.repository

import ru.mirea.kornilov.sway.domain.models.Place
import ru.mirea.kornilov.sway.domain.models.SceneType
import ru.mirea.kornilov.sway.domain.repository.PlaceRepository

class PlaceRepositoryImpl : PlaceRepository {
    private val places = listOf(
        Place(
            id = 1,
            name = "Альпы",
            country = "Швейцария",
            description = "Горные маршруты и озёра.",
            imageUrl = "https://picsum.photos/id/29/400/240",
            sceneType = SceneType.MOUNTAINS
        ),
        Place(
            id = 2,
            name = "Санторини",
            country = "Греция",
            description = "Белые дома над морем.",
            imageUrl = "https://picsum.photos/id/1015/400/240",
            sceneType = SceneType.SEA
        ),
        Place(
            id = 3,
            name = "Киото",
            country = "Япония",
            description = "Храмы и старый город.",
            imageUrl = "https://picsum.photos/id/1016/400/240",
            sceneType = SceneType.CITY
        ),
        Place(
            id = 4,
            name = "Чёрный лес",
            country = "Германия",
            description = "Тропы среди елей.",
            imageUrl = "https://picsum.photos/id/1018/400/240",
            sceneType = SceneType.FOREST
        ),
        Place(
            id = 5,
            name = "Лиссабон",
            country = "Португалия",
            description = "Трамваи и холмы у океана.",
            imageUrl = "https://picsum.photos/id/1019/400/240",
            sceneType = SceneType.CITY
        )
    )

    override fun getPlaces(): List<Place> = places

    override fun getPlaceById(id: Int): Place? = places.find { it.id == id }

    override fun getPlacesByScene(sceneType: SceneType): List<Place> {
        return places.filter { it.sceneType == sceneType }
    }
}
