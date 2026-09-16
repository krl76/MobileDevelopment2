package ru.mirea.kornilov.sway.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\n\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J,\u0010\t\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\n0\u000eH\u0016J\b\u0010\u0010\u001a\u00020\nH\u0016J,\u0010\u0011\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\n0\u000eH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lru/mirea/kornilov/sway/data/repository/AuthRepositoryImpl;", "Lru/mirea/kornilov/sway/domain/repository/AuthRepository;", "clientStorage", "Lru/mirea/kornilov/sway/data/storage/ClientStorage;", "(Lru/mirea/kornilov/sway/data/storage/ClientStorage;)V", "firebaseAuth", "Lcom/google/firebase/auth/FirebaseAuth;", "getCurrentUser", "Lru/mirea/kornilov/sway/domain/models/User;", "login", "", "", "password", "onResult", "Lkotlin/Function1;", "", "logout", "register", "data_debug"})
public final class AuthRepositoryImpl implements ru.mirea.kornilov.sway.domain.repository.AuthRepository {
    @org.jetbrains.annotations.NotNull()
    private final ru.mirea.kornilov.sway.data.storage.ClientStorage clientStorage = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.auth.FirebaseAuth firebaseAuth = null;
    
    public AuthRepositoryImpl(@org.jetbrains.annotations.NotNull()
    ru.mirea.kornilov.sway.data.storage.ClientStorage clientStorage) {
        super();
    }
    
    @java.lang.Override()
    public void login(@org.jetbrains.annotations.NotNull()
    java.lang.String login, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onResult) {
    }
    
    @java.lang.Override()
    public void register(@org.jetbrains.annotations.NotNull()
    java.lang.String login, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onResult) {
    }
    
    @java.lang.Override()
    public void logout() {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public ru.mirea.kornilov.sway.domain.models.User getCurrentUser() {
        return null;
    }
}