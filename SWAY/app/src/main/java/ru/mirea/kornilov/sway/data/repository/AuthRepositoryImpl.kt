package ru.mirea.kornilov.sway.data.repository

import ru.mirea.kornilov.sway.domain.models.User
import ru.mirea.kornilov.sway.domain.repository.AuthRepository

class AuthRepositoryImpl : AuthRepository {
    private val registered = mutableMapOf<String, String>()
    private var currentUser: User? = null

    override fun login(login: String, password: String): Boolean {
        val stored = registered[login]
        if (stored == null || stored != password) {
            return false
        }
        currentUser = User(login)
        return true
    }

    override fun register(login: String, password: String): Boolean {
        if (registered.containsKey(login)) {
            return false
        }
        registered[login] = password
        currentUser = User(login)
        return true
    }

    override fun logout() {
        currentUser = null
    }

    override fun getCurrentUser(): User? = currentUser
}
