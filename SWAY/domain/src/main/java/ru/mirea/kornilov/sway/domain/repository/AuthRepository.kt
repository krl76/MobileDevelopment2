package ru.mirea.kornilov.sway.domain.repository

import ru.mirea.kornilov.sway.domain.models.User

interface AuthRepository {
    fun login(login: String, password: String, onResult: (Boolean) -> Unit)
    fun register(login: String, password: String, onResult: (Boolean) -> Unit)
    fun logout()
    fun getCurrentUser(): User?
}
