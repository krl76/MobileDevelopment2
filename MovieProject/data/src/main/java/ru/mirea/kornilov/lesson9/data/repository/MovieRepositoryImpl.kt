package ru.mirea.kornilov.lesson9.data.repository

import ru.mirea.kornilov.lesson9.data.storage.MovieStorage
import ru.mirea.kornilov.lesson9.data.storage.models.Movie as StorageMovie
import ru.mirea.kornilov.lesson9.domain.models.Movie as DomainMovie
import ru.mirea.kornilov.lesson9.domain.repository.MovieRepository
import java.time.LocalDate

class MovieRepositoryImpl(
    private val movieStorage: MovieStorage
) : MovieRepository {

    override fun saveMovie(movie: DomainMovie): Boolean {
        return movieStorage.save(mapToStorage(movie))
    }

    override fun getMovie(): DomainMovie {
        return mapToDomain(movieStorage.get())
    }

    private fun mapToStorage(movie: DomainMovie): StorageMovie {
        return StorageMovie(
            id = 2,
            name = movie.name,
            localDate = LocalDate.now().toString()
        )
    }

    private fun mapToDomain(movie: StorageMovie): DomainMovie {
        return DomainMovie(
            id = movie.id,
            name = movie.name
        )
    }
}
