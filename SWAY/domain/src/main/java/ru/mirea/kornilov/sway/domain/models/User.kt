package ru.mirea.kornilov.sway.domain.models

data class User(
    val login: String,
    val nickname: String = "",
    val firstName: String = "",
    val lastName: String = ""
)
