package ru.mirea.kornilov.lesson9.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.mirea.kornilov.lesson9.domain.models.Movie
import ru.mirea.kornilov.lesson9.domain.repository.MovieRepository
import ru.mirea.kornilov.lesson9.domain.usecases.GetFavoriteFilmUseCase
import ru.mirea.kornilov.lesson9.domain.usecases.SaveMovieToFavoriteUseCase

class MainViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val favoriteMovie = MutableLiveData<String>()

    init {
        Log.d(MainViewModel::class.java.simpleName, "MainViewModel created")
    }

    fun getFavoriteMovie(): MutableLiveData<String> = favoriteMovie

    fun setText(movie: Movie) {
        val result = SaveMovieToFavoriteUseCase(movieRepository).execute(movie)
        favoriteMovie.value = result.toString()
    }

    fun getText() {
        val movie = GetFavoriteFilmUseCase(movieRepository).execute()
        favoriteMovie.value = if (movie.name.isEmpty()) {
            "Нет данных!"
        } else {
            String.format("My favorite movie is %s", movie.name)
        }
    }

    override fun onCleared() {
        Log.d(MainViewModel::class.java.simpleName, "MainViewModel cleared")
        super.onCleared()
    }
}
