package ru.mirea.kornilov.sway.domain.usecases

import ru.mirea.kornilov.sway.domain.models.User
import ru.mirea.kornilov.sway.domain.repository.AuthRepository

class RefreshProfileUseCase(
    private val authRepository: AuthRepository
) {
    fun execute(onResult: (User?) -> Unit) {
        authRepository.loadProfile(onResult)
    }
}
