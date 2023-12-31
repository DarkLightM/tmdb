package com.example.tmdbkotlinapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.tmdbkotlinapp.data.MovieService
import com.example.tmdbkotlinapp.data.db.dao.MovieDao
import com.example.tmdbkotlinapp.data.db.entity.MovieEntity
import com.example.tmdbkotlinapp.domain.base.WorkResult
import com.example.tmdbkotlinapp.domain.base.map
import com.example.tmdbkotlinapp.domain.models.Movie
import com.example.tmdbkotlinapp.domain.models.MovieResult
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

    override suspend fun getRandomMovieList(
        page: Int, year: Int, genre: String
    ): WorkResult<List<Movie>> {
        return movieService.getRandomMovie(page, year, genre).map { it.toDomain() }
    }

    override fun getPopularMovieList(): Flow<PagingData<Movie>> {
        return pager.flow
    }

    override suspend fun getMovieDetails(remoteId: Int): WorkResult<MovieResult> {
        val savedMovie = movieDao.getMovieByRemoteId(remoteId)
        return savedMovie?.toDomain()?.let {
            WorkResult.Success(MovieResult(true, it))
        }
            ?: getMovieFromRemote(remoteId)
    }

    override suspend fun getSavedMovies(): Flow<List<MovieEntity>> {
        return movieDao.getSavedMovies()
    }

    override suspend fun saveMovieInDb(movie: Movie) {
        val movieEntity = MovieEntity(
            id = 0,
            remoteId = movie.movieRemoteId,
            isAdult = movie.isAdult,
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

    override suspend fun deleteMovieFromDb(remoteId: Int) {
        movieDao.deleteMovie(remoteId)
    }

    override suspend fun isMovieInDb(remoteId: Int): Flow<Int> {
        return movieDao.isMovieInDb(remoteId)
    }

    private suspend fun getMovieFromRemote(remoteId: Int): WorkResult<MovieResult> {
        val movieResult = movieService.getMovieDetails(remoteId)
        val castResult = movieService.getMovieCast(remoteId).map { it.toDomain() }

        return movieResult.map { movieData ->
            val movie = movieData.toDomain()
            val cast = (castResult as? WorkResult.Success)?.data
            MovieResult(false, movie.copy(cast = cast))
        }
    }
}