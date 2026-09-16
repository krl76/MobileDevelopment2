package ru.mirea.kornilov.lesson9.data.storage

import ru.mirea.kornilov.lesson9.data.storage.models.Movie

interface MovieStorage {
    fun get(): Movie
    fun save(movie: Movie): Boolean
}
