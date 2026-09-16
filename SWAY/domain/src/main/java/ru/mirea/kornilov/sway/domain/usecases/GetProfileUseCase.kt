package ru.mirea.kornilov.sway.domain.usecases

import ru.mirea.kornilov.sway.domain.models.User
import ru.mirea.kornilov.sway.domain.repository.AuthRepository

class GetProfileUseCase(
    private val authRepository: AuthRepository
) {
    fun execute(): User? {
        return authRepository.getCurrentUser()
    }
}
