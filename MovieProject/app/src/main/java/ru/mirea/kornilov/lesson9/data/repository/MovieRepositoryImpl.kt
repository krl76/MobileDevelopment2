package ru.mirea.kornilov.lesson9.data.repository

import android.content.Context
import ru.mirea.kornilov.lesson9.domain.models.Movie
import ru.mirea.kornilov.lesson9.domain.repository.MovieRepository

class MovieRepositoryImpl(context: Context) : MovieRepository {
    private val prefs = context.applicationContext
        .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    override fun saveMovie(movie: Movie): Boolean {
        return prefs.edit()
            .putInt(KEY_ID, movie.id)
            .putString(KEY_NAME, movie.name)
            .commit()
    }

    override fun getMovie(): Movie {
        val id = prefs.getInt(KEY_ID, -1)
        val name = prefs.getString(KEY_NAME, "").orEmpty()
        return Movie(id, name)
    }

    private companion object {
        const val PREFS_NAME = "favorite_movie"
        const val KEY_ID = "movie_id"
        const val KEY_NAME = "movie_name"
    }
}
