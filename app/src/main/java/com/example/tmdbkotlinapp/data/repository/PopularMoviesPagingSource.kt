package com.example.tmdbkotlinapp.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.tmdbkotlinapp.data.MovieService
import com.example.tmdbkotlinapp.domain.models.Movie
import retrofit2.HttpException
import java.io.IOException

class PopularMoviesPagingSource(private val movieService: MovieService) :
    PagingSource<Int, Movie>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val pageIndex = params.key ?: TMDB_STARTING_PAGE_INDEX

        return try {
            val response = movieService.getPopular(pageIndex)
            if (response.isSuccessful) {
                val movies = response.body()?.toDomain()
                val nextPageNumber = if (requireNotNull(movies).isEmpty()) null else pageIndex + 1
                val prevPageNumber = if (pageIndex > 1) pageIndex - 1 else null
                LoadResult.Page(movies, prevPageNumber, nextPageNumber)
            } else {
                LoadResult.Error(HttpException(response))
            }
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }
}
