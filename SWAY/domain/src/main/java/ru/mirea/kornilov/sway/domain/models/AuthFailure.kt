package ru.mirea.kornilov.sway.domain.models

enum class AuthError {
    INVALID_CREDENTIALS,
    EMAIL_ALREADY_USED,
    WEAK_PASSWORD,
    PROFILE_NOT_SAVED,
    NETWORK
}

data class AuthFailure(
    val error: AuthError,
    val detail: String = ""
)
