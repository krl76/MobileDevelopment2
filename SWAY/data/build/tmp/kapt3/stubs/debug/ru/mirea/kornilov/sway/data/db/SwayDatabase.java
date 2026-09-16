package ru.mirea.kornilov.sway.data.db;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&\u00a8\u0006\u0006"}, d2 = {"Lru/mirea/kornilov/sway/data/db/SwayDatabase;", "Landroidx/room/RoomDatabase;", "()V", "tripDao", "Lru/mirea/kornilov/sway/data/db/TripDao;", "Companion", "data_debug"})
@androidx.room.Database(entities = {ru.mirea.kornilov.sway.data.db.TripEntity.class}, version = 1, exportSchema = false)
public abstract class SwayDatabase extends androidx.room.RoomDatabase {
    @org.jetbrains.annotations.NotNull()
    public static final ru.mirea.kornilov.sway.data.db.SwayDatabase.Companion Companion = null;
    
    public SwayDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract ru.mirea.kornilov.sway.data.db.TripDao tripDao();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lru/mirea/kornilov/sway/data/db/SwayDatabase$Companion;", "", "()V", "create", "Lru/mirea/kornilov/sway/data/db/SwayDatabase;", "context", "Landroid/content/Context;", "data_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final ru.mirea.kornilov.sway.data.db.SwayDatabase create(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
}