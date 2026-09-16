package ru.mirea.kornilov.lesson9.domain.repository

import ru.mirea.kornilov.lesson9.domain.models.Movie

interface MovieRepository {
    fun saveMovie(movie: Movie): Boolean
    fun getMovie(): Movie
}
