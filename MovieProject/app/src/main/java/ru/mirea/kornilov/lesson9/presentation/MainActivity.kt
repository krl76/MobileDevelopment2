package ru.mirea.kornilov.lesson9.presentation

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ru.mirea.kornilov.lesson9.R
import ru.mirea.kornilov.lesson9.data.repository.MovieRepositoryImpl
import ru.mirea.kornilov.lesson9.data.storage.MovieStorage
import ru.mirea.kornilov.lesson9.data.storage.sharedprefs.SharedPrefMovieStorage
import ru.mirea.kornilov.lesson9.domain.models.Movie
import ru.mirea.kornilov.lesson9.domain.repository.MovieRepository
import ru.mirea.kornilov.lesson9.domain.usecases.GetFavoriteFilmUseCase
import ru.mirea.kornilov.lesson9.domain.usecases.SaveMovieToFavoriteUseCase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val text = findViewById<EditText>(R.id.editTextMovie)
        val textView = findViewById<TextView>(R.id.textViewMovie)
        val movieStorage: MovieStorage = SharedPrefMovieStorage(this)
        val movieRepository: MovieRepository = MovieRepositoryImpl(movieStorage)

        findViewById<View>(R.id.buttonSaveMovie).setOnClickListener {
            val result = SaveMovieToFavoriteUseCase(movieRepository)
                .execute(Movie(2, text.text.toString()))
            textView.text = String.format("Save result %s", result)
        }

        findViewById<View>(R.id.buttonGetMovie).setOnClickListener {
            val movie = GetFavoriteFilmUseCase(movieRepository).execute()
            textView.text = if (movie.name.isEmpty()) {
                getString(R.string.no_data)
            } else {
                movie.name
            }
        }
    }
}
