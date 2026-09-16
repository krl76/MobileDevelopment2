package ru.mirea.kornilov.sway.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lru/mirea/kornilov/sway/data/repository/WeatherRepositoryImpl;", "Lru/mirea/kornilov/sway/domain/repository/WeatherRepository;", "networkApi", "Lru/mirea/kornilov/sway/data/network/NetworkApi;", "(Lru/mirea/kornilov/sway/data/network/NetworkApi;)V", "getWeather", "Lru/mirea/kornilov/sway/domain/models/Weather;", "placeId", "", "data_debug"})
public final class WeatherRepositoryImpl implements ru.mirea.kornilov.sway.domain.repository.WeatherRepository {
    @org.jetbrains.annotations.NotNull()
    private final ru.mirea.kornilov.sway.data.network.NetworkApi networkApi = null;
    
    public WeatherRepositoryImpl(@org.jetbrains.annotations.NotNull()
    ru.mirea.kornilov.sway.data.network.NetworkApi networkApi) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public ru.mirea.kornilov.sway.domain.models.Weather getWeather(int placeId) {
        return null;
    }
}