package com.example.tmdbkotlinapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.tmdbkotlinapp.data.MovieService
import com.example.tmdbkotlinapp.data.db.dao.MovieDao
import com.example.tmdbkotlinapp.data.db.entity.MovieEntity
import com.example.tmdbkotlinapp.domain.models.Movie
import com.example.tmdbkotlinapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieService, private val movieDao: MovieDao
) : MovieRepository {

    private val pager = Pager(config = PagingConfig(
        pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false
    ), pagingSourceFactory = {
        PopularMoviesPagingSource(movieService)
    })

    override suspend fun getRandomMovieList(page: Int, year: Int, genre: String): List<Movie> {
        return movieService.getRandomMovie(page, year, genre).toDomain()
    }

    override fun getPopularMovieList(): Flow<PagingData<Movie>> {
        return pager.flow
    }

    override suspend fun getMovieDetails(id: Int, remoteId: Int): Movie {
        return if (id >= 0) {
            val savedMovie = movieDao.getMovieById(id)
            savedMovie?.toDomain() ?: getMovieFromRemote(remoteId)
        } else {
            getMovieFromRemote(remoteId)
        }

    }

    override suspend fun getSavedMovies(): Flow<List<MovieEntity>> {
        return movieDao.getSavedMovies()
    }

    override suspend fun saveMovieInDb(movie: Movie) {
        val movieEntity = MovieEntity(
            id = 0,
            remoteId = movie.movieRemoteId,
            title = movie.originalTitle ?: "",
            overview = movie.overview,
            releaseDate = movie.releaseDate,
            rating = movie.rating,
            posterPath = movie.posterPath,
            genre = movie.genreList ?: emptyList(),
            actors = movie.cast ?: emptyList()
        )
        movieDao.deleteMovie(movie.movieRemoteId)
        movieDao.insertMovie(movieEntity)
    }

    private suspend fun getMovieFromRemote(remoteId: Int): Movie {
        val movie = movieService.getMovieDetails(remoteId).toDomain()
        val cast = movieService.getMovieCast(remoteId).toDomain()
        return movie.copy(cast = cast)
    }
}