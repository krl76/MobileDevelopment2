package ru.mirea.kornilov.sway.domain.usecases

import ru.mirea.kornilov.sway.domain.models.AuthError
import ru.mirea.kornilov.sway.domain.models.AuthFailure
import ru.mirea.kornilov.sway.domain.repository.AuthRepository

class RegisterUseCase(
    private val authRepository: AuthRepository
) {
    fun execute(
        login: String,
        password: String,
        nickname: String,
        firstName: String,
        lastName: String,
        onResult: (AuthFailure?) -> Unit
    ) {
        if (login.isBlank() || password.isBlank() || nickname.isBlank() || firstName.isBlank() || lastName.isBlank()) {
            onResult(AuthFailure(AuthError.INVALID_CREDENTIALS))
            return
        }
        authRepository.register(
            login.trim(),
            password,
            nickname.trim(),
            firstName.trim(),
            lastName.trim(),
            onResult
        )
    }
}
