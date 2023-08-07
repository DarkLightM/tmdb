package com.example.tmdbkotlinapp.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.tmdbkotlinapp.domain.repository.MovieRepository
import com.example.tmdbkotlinapp.data.MovieService
import com.example.tmdbkotlinapp.domain.models.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val movieService: MovieService) :
    MovieRepository {

    override suspend fun getRandomMovieList(page: Int, year: Int, genre: String): List<Movie> {
        return movieService.getRandomMovie(page, year, genre).toDomain()
    }

    override fun getPopularMovieList(): LiveData<PagingData<Movie>> {
        val pager = Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PopularMoviesPagingSource(movieService)
            }
        )
        val liveData = pager.liveData

        liveData.observeForever { pagingData ->
            Log.i("Repository", pagingData.toString())
        }

        return liveData
    }

    override suspend fun getMovieDetails(id: Int): Movie {
        return movieService.getMovieDetails(id).toDomain()
    }

    override suspend fun getTotalPages(year: Int, genre: String): Int {
        return movieService.getRandomMovie(1, year, genre).totalPages
    }
}