package ru.mirea.kornilov.sway.data.storage

interface ClientStorage {
    fun saveLogin(login: String)
    fun getLogin(): String?
    fun saveProfile(login: String, nickname: String, firstName: String, lastName: String)
    fun getNickname(login: String): String
    fun getFirstName(login: String): String
    fun getLastName(login: String): String
    fun clear()
}
