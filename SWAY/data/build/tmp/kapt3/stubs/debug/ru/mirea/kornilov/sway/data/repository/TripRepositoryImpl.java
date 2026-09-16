package ru.mirea.kornilov.sway.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0016J\u0010\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lru/mirea/kornilov/sway/data/repository/TripRepositoryImpl;", "Lru/mirea/kornilov/sway/domain/repository/TripRepository;", "tripDao", "Lru/mirea/kornilov/sway/data/db/TripDao;", "(Lru/mirea/kornilov/sway/data/db/TripDao;)V", "addNote", "", "placeId", "", "note", "", "getTrips", "", "Lru/mirea/kornilov/sway/domain/models/Trip;", "markVisited", "saveWantToVisit", "Companion", "data_debug"})
public final class TripRepositoryImpl implements ru.mirea.kornilov.sway.domain.repository.TripRepository {
    @org.jetbrains.annotations.NotNull()
    private final ru.mirea.kornilov.sway.data.db.TripDao tripDao = null;
    @org.jetbrains.annotations.NotNull()
    public static final ru.mirea.kornilov.sway.data.repository.TripRepositoryImpl.Companion Companion = null;
    
    private TripRepositoryImpl(ru.mirea.kornilov.sway.data.db.TripDao tripDao) {
        super();
    }
    
    @java.lang.Override()
    public boolean saveWantToVisit(int placeId) {
        return false;
    }
    
    @java.lang.Override()
    public boolean markVisited(int placeId) {
        return false;
    }
    
    @java.lang.Override()
    public boolean addNote(int placeId, @org.jetbrains.annotations.NotNull()
    java.lang.String note) {
        return false;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.util.List<ru.mirea.kornilov.sway.domain.models.Trip> getTrips() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lru/mirea/kornilov/sway/data/repository/TripRepositoryImpl$Companion;", "", "()V", "create", "Lru/mirea/kornilov/sway/domain/repository/TripRepository;", "context", "Landroid/content/Context;", "data_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final ru.mirea.kornilov.sway.domain.repository.TripRepository create(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
}