package ru.mirea.kornilov.sway.domain.usecases

import ru.mirea.kornilov.sway.domain.repository.AuthRepository

class LoginUseCase(
    private val authRepository: AuthRepository
) {
    fun execute(login: String, password: String, onResult: (Boolean) -> Unit) {
        if (login.isBlank() || password.isBlank()) {
            onResult(false)
            return
        }
        authRepository.login(login.trim(), password, onResult)
    }
}
