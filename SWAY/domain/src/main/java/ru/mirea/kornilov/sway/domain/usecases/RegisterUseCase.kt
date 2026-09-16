package ru.mirea.kornilov.sway.domain.usecases

import ru.mirea.kornilov.sway.domain.repository.AuthRepository

class RegisterUseCase(
    private val authRepository: AuthRepository
) {
    fun execute(login: String, password: String, onResult: (Boolean) -> Unit) {
        if (login.isBlank() || password.isBlank()) {
            onResult(false)
            return
        }
        authRepository.register(login.trim(), password, onResult)
    }
}
