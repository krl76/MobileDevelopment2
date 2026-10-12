package ru.mirea.kornilov.sway.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000 #2\u00020\u0001:\u0001#B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\t\u001a\u00020\n2\u000e\u0010\u000b\u001a\n\u0018\u00010\fj\u0004\u0018\u0001`\rH\u0002J(\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0011H\u0002J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\n\u0010\u0017\u001a\u0004\u0018\u00010\u0016H\u0016J\u001e\u0010\u0018\u001a\u00020\u000f2\u0014\u0010\u0019\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0016\u0012\u0004\u0012\u00020\u000f0\u001aH\u0016J.\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u00112\u0014\u0010\u0019\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\n\u0012\u0004\u0012\u00020\u000f0\u001aH\u0016J\b\u0010\u001d\u001a\u00020\u000fH\u0016J\u001c\u0010\u001e\u001a\u00020\u000f2\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u000f0\u001aH\u0002JF\u0010!\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00112\u0014\u0010\u0019\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\n\u0012\u0004\u0012\u00020\u000f0\u001aH\u0016J6\u0010\"\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00112\u0014\u0010\u0019\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\n\u0012\u0004\u0012\u00020\u000f0\u001aH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006$"}, d2 = {"Lru/mirea/kornilov/sway/data/repository/AuthRepositoryImpl;", "Lru/mirea/kornilov/sway/domain/repository/AuthRepository;", "clientStorage", "Lru/mirea/kornilov/sway/data/storage/ClientStorage;", "(Lru/mirea/kornilov/sway/data/storage/ClientStorage;)V", "firebaseAuth", "Lcom/google/firebase/auth/FirebaseAuth;", "firestore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "authFailure", "Lru/mirea/kornilov/sway/domain/models/AuthFailure;", "error", "Ljava/lang/Exception;", "Lkotlin/Exception;", "cacheProfile", "", "email", "", "nickname", "firstName", "lastName", "cachedUser", "Lru/mirea/kornilov/sway/domain/models/User;", "getCurrentUser", "loadProfile", "onResult", "Lkotlin/Function1;", "login", "password", "logout", "readProfile", "onDone", "", "register", "updateProfile", "Companion", "data_debug"})
public final class AuthRepositoryImpl implements ru.mirea.kornilov.sway.domain.repository.AuthRepository {
    @org.jetbrains.annotations.NotNull()
    private final ru.mirea.kornilov.sway.data.storage.ClientStorage clientStorage = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.auth.FirebaseAuth firebaseAuth = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.FirebaseFirestore firestore = null;
    @org.jetbrains.annotations.NotNull()
    @java.lang.Deprecated()
    public static final java.lang.String TAG = "AuthRepository";
    @org.jetbrains.annotations.NotNull()
    @java.lang.Deprecated()
    public static final java.lang.String USERS = "users";
    @org.jetbrains.annotations.NotNull()
    private static final ru.mirea.kornilov.sway.data.repository.AuthRepositoryImpl.Companion Companion = null;
    
    public AuthRepositoryImpl(@org.jetbrains.annotations.NotNull()
    ru.mirea.kornilov.sway.data.storage.ClientStorage clientStorage) {
        super();
    }
    
    @java.lang.Override()
    public void login(@org.jetbrains.annotations.NotNull()
    java.lang.String login, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super ru.mirea.kornilov.sway.domain.models.AuthFailure, kotlin.Unit> onResult) {
    }
    
    @java.lang.Override()
    public void register(@org.jetbrains.annotations.NotNull()
    java.lang.String login, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    java.lang.String nickname, @org.jetbrains.annotations.NotNull()
    java.lang.String firstName, @org.jetbrains.annotations.NotNull()
    java.lang.String lastName, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super ru.mirea.kornilov.sway.domain.models.AuthFailure, kotlin.Unit> onResult) {
    }
    
    @java.lang.Override()
    public void updateProfile(@org.jetbrains.annotations.NotNull()
    java.lang.String nickname, @org.jetbrains.annotations.NotNull()
    java.lang.String firstName, @org.jetbrains.annotations.NotNull()
    java.lang.String lastName, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super ru.mirea.kornilov.sway.domain.models.AuthFailure, kotlin.Unit> onResult) {
    }
    
    @java.lang.Override()
    public void logout() {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public ru.mirea.kornilov.sway.domain.models.User getCurrentUser() {
        return null;
    }
    
    @java.lang.Override()
    public void loadProfile(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super ru.mirea.kornilov.sway.domain.models.User, kotlin.Unit> onResult) {
    }
    
    private final void readProfile(kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onDone) {
    }
    
    private final void cacheProfile(java.lang.String email, java.lang.String nickname, java.lang.String firstName, java.lang.String lastName) {
    }
    
    private final ru.mirea.kornilov.sway.domain.models.AuthFailure authFailure(java.lang.Exception error) {
        return null;
    }
    
    private final ru.mirea.kornilov.sway.domain.models.User cachedUser(java.lang.String email) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lru/mirea/kornilov/sway/data/repository/AuthRepositoryImpl$Companion;", "", "()V", "TAG", "", "USERS", "data_debug"})
    static final class Companion {
        
        private Companion() {
            super();
        }
    }
}