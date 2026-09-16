package ru.mirea.kornilov.sway.domain.usecases

import ru.mirea.kornilov.sway.domain.repository.AuthRepository

class LoginUseCase(
    private val authRepository: AuthRepository
) {
    fun execute(login: String, password: String): Boolean {
        if (login.isBlank() || password.isBlank()) {
            return false
        }
        return authRepository.login(login.trim(), password)
    }
}
