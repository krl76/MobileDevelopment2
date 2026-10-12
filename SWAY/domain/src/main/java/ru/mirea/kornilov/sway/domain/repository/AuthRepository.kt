package ru.mirea.kornilov.sway.domain.repository

import ru.mirea.kornilov.sway.domain.models.AuthFailure
import ru.mirea.kornilov.sway.domain.models.User

interface AuthRepository {
    fun login(login: String, password: String, onResult: (AuthFailure?) -> Unit)
    fun register(
        login: String,
        password: String,
        nickname: String,
        firstName: String,
        lastName: String,
        onResult: (AuthFailure?) -> Unit
    )
    fun updateProfile(
        nickname: String,
        firstName: String,
        lastName: String,
        onResult: (AuthFailure?) -> Unit
    )
    fun logout()
    fun getCurrentUser(): User?
    fun loadProfile(onResult: (User?) -> Unit)
}
