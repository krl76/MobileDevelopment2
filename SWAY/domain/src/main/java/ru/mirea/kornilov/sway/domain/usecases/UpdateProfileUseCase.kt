package ru.mirea.kornilov.sway.domain.usecases

import ru.mirea.kornilov.sway.domain.models.AuthError
import ru.mirea.kornilov.sway.domain.models.AuthFailure
import ru.mirea.kornilov.sway.domain.repository.AuthRepository

class UpdateProfileUseCase(
    private val authRepository: AuthRepository
) {
    fun execute(
        nickname: String,
        firstName: String,
        lastName: String,
        onResult: (AuthFailure?) -> Unit
    ) {
        if (nickname.isBlank() || firstName.isBlank() || lastName.isBlank()) {
            onResult(AuthFailure(AuthError.INVALID_CREDENTIALS))
            return
        }
        authRepository.updateProfile(nickname.trim(), firstName.trim(), lastName.trim(), onResult)
    }
}
