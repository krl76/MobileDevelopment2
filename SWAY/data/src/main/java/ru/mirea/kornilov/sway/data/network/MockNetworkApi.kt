package ru.mirea.kornilov.sway.data.network

import ru.mirea.kornilov.sway.data.network.models.PlaceDto
import ru.mirea.kornilov.sway.data.network.models.WeatherDto

class MockNetworkApi : NetworkApi {
    private val places = listOf(
        PlaceDto(1, "Альпы", "Швейцария", "Горные маршруты и озёра.", "https://picsum.photos/id/29/400/240", "MOUNTAINS"),
        PlaceDto(2, "Санторини", "Греция", "Белые дома над морем.", "https://picsum.photos/id/1015/400/240", "SEA"),
        PlaceDto(3, "Киото", "Япония", "Храмы и старый город.", "https://picsum.photos/id/1016/400/240", "CITY"),
        PlaceDto(4, "Чёрный лес", "Германия", "Тропы среди елей.", "https://picsum.photos/id/1018/400/240", "FOREST"),
        PlaceDto(5, "Лиссабон", "Португалия", "Трамваи и холмы у океана.", "https://picsum.photos/id/1019/400/240", "CITY"),
        PlaceDto(6, "Каппадокия", "Турция", "Шары над долинами.", "https://picsum.photos/id/1011/400/240", "MOUNTAINS"),
        PlaceDto(7, "Байкал", "Россия", "Лед и скалы у воды.", "https://picsum.photos/id/1036/400/240", "SEA"),
        PlaceDto(8, "Рейкьявик", "Исландия", "Цветные дома и океан.", "https://picsum.photos/id/1039/400/240", "CITY")
    )

    private val weather = mapOf(
        1 to WeatherDto(1, 8, "облачно, ветер"),
        2 to WeatherDto(2, 24, "ясно"),
        3 to WeatherDto(3, 16, "небольшой дождь"),
        4 to WeatherDto(4, 12, "туман"),
        5 to WeatherDto(5, 21, "переменная облачность"),
        6 to WeatherDto(6, 19, "ясно"),
        7 to WeatherDto(7, 2, "морозно"),
        8 to WeatherDto(8, 7, "ветер")
    )

    override fun getPlaces(): List<PlaceDto> = places

    override fun getPlaceById(id: Int): PlaceDto? = places.find { it.id == id }

    override fun getWeather(placeId: Int): WeatherDto {
        return weather[placeId] ?: WeatherDto(placeId, 18, "нет данных")
    }
}
