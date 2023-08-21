package com.example.tmdbkotlinapp.ui.popular_movies

import androidx.paging.PagingData
import com.example.tmdbkotlinapp.domain.models.Movie
import com.example.tmdbkotlinapp.ui.base.UiState

sealed class PopularUiState : UiState {
    object Loading : PopularUiState()
    class Content (val pagingData: PagingData<Movie>) : PopularUiState()
}