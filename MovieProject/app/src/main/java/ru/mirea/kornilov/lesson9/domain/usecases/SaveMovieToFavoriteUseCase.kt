package ru.mirea.kornilov.lesson9.domain.usecases

import ru.mirea.kornilov.lesson9.domain.models.Movie
import ru.mirea.kornilov.lesson9.domain.repository.MovieRepository

class SaveMovieToFavoriteUseCase(
    private val movieRepository: MovieRepository
) {
    fun execute(movie: Movie): Boolean {
        if (movie.name.isBlank()) {
            return false
        }
        return movieRepository.saveMovie(movie)
    }
}
