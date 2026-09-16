package ru.mirea.kornilov.lesson9.data.storage.sharedprefs

import android.content.Context
import ru.mirea.kornilov.lesson9.data.storage.MovieStorage
import ru.mirea.kornilov.lesson9.data.storage.models.Movie
import java.time.LocalDate

class SharedPrefMovieStorage(context: Context) : MovieStorage {
    private val sharedPreferences = context.applicationContext
        .getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    override fun get(): Movie {
        val movieName = sharedPreferences.getString(KEY, "") ?: ""
        val movieDate = sharedPreferences.getString(DATE_KEY, LocalDate.now().toString())
            ?: LocalDate.now().toString()
        val movieId = sharedPreferences.getInt(ID_KEY, -1)
        return Movie(movieId, movieName, movieDate)
    }

    override fun save(movie: Movie): Boolean {
        return sharedPreferences.edit()
            .putString(KEY, movie.name)
            .putString(DATE_KEY, LocalDate.now().toString())
            .putInt(ID_KEY, movie.id)
            .commit()
    }

    private companion object {
        const val SHARED_PREFS_NAME = "shared_prefs_name"
        const val KEY = "movie_name"
        const val DATE_KEY = "movie_date"
        const val ID_KEY = "movie_id"
    }
}
