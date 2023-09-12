package com.example.tmdbkotlinapp.ui.genres_bottom_sheet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbkotlinapp.data.remote.utils.NetworkException
import com.example.tmdbkotlinapp.domain.base.WorkResult
import com.example.tmdbkotlinapp.domain.base.handle
import com.example.tmdbkotlinapp.domain.models.Genre
import com.example.tmdbkotlinapp.domain.repository.GenreRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class GenreBottomSheetViewModel @Inject constructor(private val genreRepository: GenreRepository) :
    ViewModel() {

    private val _genres = MutableLiveData<List<Genre>>()
    val genres: LiveData<List<Genre>> get() = _genres

    private val _selectedGenre = MutableLiveData<String>()
    val selectedGenre: LiveData<String> get() = _selectedGenre

    private val _networkError = MutableLiveData<Boolean>()
    val networkError: LiveData<Boolean> get() = _networkError

    init {
        loadGenres()
    }

    fun loadGenres() {
        viewModelScope.launch {
            val workResult = genreRepository.getGenres()
            workResult.handle(
                onSuccess = {
                    _genres.postValue(it)
                    _networkError.postValue(false)
                },
                onNotSuccess = { handleErrorResult(it) })
        }
    }

    fun setSelectedGenre(genreName: String) {
        _selectedGenre.value = genreName
    }

    private fun handleErrorResult(workResult: WorkResult<List<Genre>>) {
        when (workResult) {
            is WorkResult.Fail -> {
                if (workResult.exception is NetworkException) {
                    _networkError.postValue(true)
                }
            }
            else -> {}
        }
    }
}