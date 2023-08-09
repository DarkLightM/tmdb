package com.example.tmdbkotlinapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.tmdbkotlinapp.data.MovieService
import com.example.tmdbkotlinapp.domain.models.Movie
import com.example.tmdbkotlinapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val movieService: MovieService) :
    MovieRepository {

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

    override suspend fun getMovieDetails(id: Int): Movie {
        return movieService.getMovieDetails(id).toDomain()
    }

    override suspend fun getTotalPages(year: Int, genre: String): Int {
        return movieService.getRandomMovie(1, year, genre).totalPages
    }
}