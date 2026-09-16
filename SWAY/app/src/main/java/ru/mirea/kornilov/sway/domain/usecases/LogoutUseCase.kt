package ru.mirea.kornilov.sway.domain.usecases

import ru.mirea.kornilov.sway.domain.repository.AuthRepository

class LogoutUseCase(
    private val authRepository: AuthRepository
) {
    fun execute() {
        authRepository.logout()
    }
}
