package ru.mirea.kornilov.lesson9.domain.usecases

import ru.mirea.kornilov.lesson9.domain.models.Movie
import ru.mirea.kornilov.lesson9.domain.repository.MovieRepository

class GetFavoriteFilmUseCase(
    private val movieRepository: MovieRepository
) {
    fun execute(): Movie {
        return movieRepository.getMovie()
    }
}
