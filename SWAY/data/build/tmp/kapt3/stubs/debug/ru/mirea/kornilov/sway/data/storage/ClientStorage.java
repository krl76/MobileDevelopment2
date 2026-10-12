package ru.mirea.kornilov.sway.data.storage;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J\u0010\u0010\u0007\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J\n\u0010\b\u001a\u0004\u0018\u00010\u0005H&J\u0010\u0010\t\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H&J(\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u0005H&\u00a8\u0006\u000f"}, d2 = {"Lru/mirea/kornilov/sway/data/storage/ClientStorage;", "", "clear", "", "getFirstName", "", "login", "getLastName", "getLogin", "getNickname", "saveLogin", "saveProfile", "nickname", "firstName", "lastName", "data_debug"})
public abstract interface ClientStorage {
    
    public abstract void saveLogin(@org.jetbrains.annotations.NotNull()
    java.lang.String login);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.String getLogin();
    
    public abstract void saveProfile(@org.jetbrains.annotations.NotNull()
    java.lang.String login, @org.jetbrains.annotations.NotNull()
    java.lang.String nickname, @org.jetbrains.annotations.NotNull()
    java.lang.String firstName, @org.jetbrains.annotations.NotNull()
    java.lang.String lastName);
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.lang.String getNickname(@org.jetbrains.annotations.NotNull()
    java.lang.String login);
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.lang.String getFirstName(@org.jetbrains.annotations.NotNull()
    java.lang.String login);
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.lang.String getLastName(@org.jetbrains.annotations.NotNull()
    java.lang.String login);
    
    public abstract void clear();
}