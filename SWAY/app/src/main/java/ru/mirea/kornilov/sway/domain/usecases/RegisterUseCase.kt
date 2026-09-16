package ru.mirea.kornilov.sway.domain.usecases

import ru.mirea.kornilov.sway.domain.repository.AuthRepository

class RegisterUseCase(
    private val authRepository: AuthRepository
) {
    fun execute(login: String, password: String): Boolean {
        if (login.isBlank() || password.isBlank()) {
            return false
        }
        return authRepository.register(login.trim(), password)
    }
}
