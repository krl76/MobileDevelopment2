package ru.mirea.kornilov.sway.data.storage.sharedprefs;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\b\u001a\u00020\tH\u0016J\n\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000bH\u0016R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lru/mirea/kornilov/sway/data/storage/sharedprefs/SharedPrefClientStorage;", "Lru/mirea/kornilov/sway/data/storage/ClientStorage;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "prefs", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "clear", "", "getLogin", "", "saveLogin", "login", "Companion", "data_debug"})
public final class SharedPrefClientStorage implements ru.mirea.kornilov.sway.data.storage.ClientStorage {
    private final android.content.SharedPreferences prefs = null;
    @org.jetbrains.annotations.NotNull()
    @java.lang.Deprecated()
    public static final java.lang.String PREFS_NAME = "sway_client";
    @org.jetbrains.annotations.NotNull()
    @java.lang.Deprecated()
    public static final java.lang.String KEY_LOGIN = "client_login";
    @org.jetbrains.annotations.NotNull()
    private static final ru.mirea.kornilov.sway.data.storage.sharedprefs.SharedPrefClientStorage.Companion Companion = null;
    
    public SharedPrefClientStorage(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @java.lang.Override()
    public void saveLogin(@org.jetbrains.annotations.NotNull()
    java.lang.String login) {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.String getLogin() {
        return null;
    }
    
    @java.lang.Override()
    public void clear() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lru/mirea/kornilov/sway/data/storage/sharedprefs/SharedPrefClientStorage$Companion;", "", "()V", "KEY_LOGIN", "", "PREFS_NAME", "data_debug"})
    static final class Companion {
        
        private Companion() {
            super();
        }
    }
}