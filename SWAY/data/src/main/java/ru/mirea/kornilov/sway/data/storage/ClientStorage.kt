package ru.mirea.kornilov.sway.data.storage

interface ClientStorage {
    fun saveLogin(login: String)
    fun getLogin(): String?
    fun clear()
}
