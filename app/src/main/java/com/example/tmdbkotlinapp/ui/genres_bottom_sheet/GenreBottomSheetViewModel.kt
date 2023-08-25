package com.example.tmdbkotlinapp.ui.genres_bottom_sheet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> get() = _error

    init {
        loadGenres()
    }

    private fun loadGenres() {
        viewModelScope.launch {
            val workResult = genreRepository.getGenres()
            workResult.handle(
                onSuccess = {
                    _genres.postValue(it)
                    _error.postValue(false)
                },
                onNotSuccess = { _error.postValue(true) })
        }
    }

    fun setSelectedGenre(genreName: String) {
        _selectedGenre.value = genreName
    }
}