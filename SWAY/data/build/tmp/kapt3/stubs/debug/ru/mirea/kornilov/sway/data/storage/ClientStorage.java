package ru.mirea.kornilov.sway.data.storage;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\n\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0005H&\u00a8\u0006\b"}, d2 = {"Lru/mirea/kornilov/sway/data/storage/ClientStorage;", "", "clear", "", "getLogin", "", "saveLogin", "login", "data_debug"})
public abstract interface ClientStorage {
    
    public abstract void saveLogin(@org.jetbrains.annotations.NotNull()
    java.lang.String login);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.String getLogin();
    
    public abstract void clear();
}