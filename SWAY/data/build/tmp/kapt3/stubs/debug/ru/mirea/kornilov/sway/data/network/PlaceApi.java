package ru.mirea.kornilov.sway.data.network;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006J\u0014\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\'\u00a8\u0006\u0007"}, d2 = {"Lru/mirea/kornilov/sway/data/network/PlaceApi;", "", "getPlaces", "Lretrofit2/Call;", "", "Lru/mirea/kornilov/sway/data/network/models/PlaceDto;", "Companion", "data_debug"})
public abstract interface PlaceApi {
    @org.jetbrains.annotations.NotNull()
    public static final ru.mirea.kornilov.sway.data.network.PlaceApi.Companion Companion = null;
    
    @retrofit2.http.GET(value = "SWAY/data/src/main/assets/places.json")
    @org.jetbrains.annotations.NotNull()
    public abstract retrofit2.Call<java.util.List<ru.mirea.kornilov.sway.data.network.models.PlaceDto>> getPlaces();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lru/mirea/kornilov/sway/data/network/PlaceApi$Companion;", "", "()V", "BASE_URL", "", "create", "Lru/mirea/kornilov/sway/data/network/PlaceApi;", "data_debug"})
    public static final class Companion {
        @org.jetbrains.annotations.NotNull()
        private static final java.lang.String BASE_URL = "https://raw.githubusercontent.com/krl76/MobileDevelopment2/main/";
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final ru.mirea.kornilov.sway.data.network.PlaceApi create() {
            return null;
        }
    }
}