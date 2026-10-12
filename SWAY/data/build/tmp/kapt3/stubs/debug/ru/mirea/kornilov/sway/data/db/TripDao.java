package ru.mirea.kornilov.sway.data.db;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\'J\u001a\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\'J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0004H\'\u00a8\u0006\r"}, d2 = {"Lru/mirea/kornilov/sway/data/db/TripDao;", "", "getAll", "", "Lru/mirea/kornilov/sway/data/db/TripEntity;", "userId", "", "getByPlace", "placeId", "", "upsert", "", "entity", "data_debug"})
@androidx.room.Dao()
public abstract interface TripDao {
    
    @androidx.room.Query(value = "SELECT * FROM trips WHERE userId = :userId")
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.List<ru.mirea.kornilov.sway.data.db.TripEntity> getAll(@org.jetbrains.annotations.NotNull()
    java.lang.String userId);
    
    @androidx.room.Query(value = "SELECT * FROM trips WHERE userId = :userId AND placeId = :placeId LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract ru.mirea.kornilov.sway.data.db.TripEntity getByPlace(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, int placeId);
    
    @androidx.room.Insert(onConflict = 1)
    public abstract void upsert(@org.jetbrains.annotations.NotNull()
    ru.mirea.kornilov.sway.data.db.TripEntity entity);
}