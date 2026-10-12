package ru.mirea.kornilov.sway.domain.usecases

import ru.mirea.kornilov.sway.domain.models.AuthError
import ru.mirea.kornilov.sway.domain.models.AuthFailure
import ru.mirea.kornilov.sway.domain.repository.AuthRepository

class LoginUseCase(
    private val authRepository: AuthRepository
) {
    fun execute(login: String, password: String, onResult: (AuthFailure?) -> Unit) {
        if (login.isBlank() || password.isBlank()) {
            onResult(AuthFailure(AuthError.INVALID_CREDENTIALS))
            return
        }
        authRepository.login(login.trim(), password, onResult)
    }
}
