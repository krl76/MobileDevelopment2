package ru.mirea.kornilov.sway.data.network;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000b\u001a\u00020\bH\u0016J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016J\u0010\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\bH\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lru/mirea/kornilov/sway/data/network/MockNetworkApi;", "Lru/mirea/kornilov/sway/data/network/NetworkApi;", "()V", "places", "", "Lru/mirea/kornilov/sway/data/network/models/PlaceDto;", "weather", "", "", "Lru/mirea/kornilov/sway/data/network/models/WeatherDto;", "getPlaceById", "id", "getPlaces", "getWeather", "placeId", "data_debug"})
public final class MockNetworkApi implements ru.mirea.kornilov.sway.data.network.NetworkApi {
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<ru.mirea.kornilov.sway.data.network.models.PlaceDto> places = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Map<java.lang.Integer, ru.mirea.kornilov.sway.data.network.models.WeatherDto> weather = null;
    
    public MockNetworkApi() {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.util.List<ru.mirea.kornilov.sway.data.network.models.PlaceDto> getPlaces() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public ru.mirea.kornilov.sway.data.network.models.PlaceDto getPlaceById(int id) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public ru.mirea.kornilov.sway.data.network.models.WeatherDto getWeather(int placeId) {
        return null;
    }
}