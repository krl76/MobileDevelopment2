package ru.mirea.kornilov.sway.data.network;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007H&J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H&\u00a8\u0006\u000b"}, d2 = {"Lru/mirea/kornilov/sway/data/network/NetworkApi;", "", "getPlaceById", "Lru/mirea/kornilov/sway/data/network/models/PlaceDto;", "id", "", "getPlaces", "", "getWeather", "Lru/mirea/kornilov/sway/data/network/models/WeatherDto;", "placeId", "data_debug"})
public abstract interface NetworkApi {
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.List<ru.mirea.kornilov.sway.data.network.models.PlaceDto> getPlaces();
    
    @org.jetbrains.annotations.Nullable()
    public abstract ru.mirea.kornilov.sway.data.network.models.PlaceDto getPlaceById(int id);
    
    @org.jetbrains.annotations.NotNull()
    public abstract ru.mirea.kornilov.sway.data.network.models.WeatherDto getWeather(int placeId);
}