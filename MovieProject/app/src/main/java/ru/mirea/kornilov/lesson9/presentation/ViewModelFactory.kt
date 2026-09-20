package ru.mirea.kornilov.lesson9.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.mirea.kornilov.lesson9.data.repository.MovieRepositoryImpl
import ru.mirea.kornilov.lesson9.data.storage.MovieStorage
import ru.mirea.kornilov.lesson9.data.storage.sharedprefs.SharedPrefMovieStorage
import ru.mirea.kornilov.lesson9.domain.repository.MovieRepository

class ViewModelFactory(
    context: Context
) : ViewModelProvider.Factory {
    private val context = context.applicationContext

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val sharedPrefMovieStorage: MovieStorage = SharedPrefMovieStorage(context)
        val movieRepository: MovieRepository = MovieRepositoryImpl(sharedPrefMovieStorage)
        return MainViewModel(movieRepository) as T
    }
}
