package com.example.tmdbkotlinapp.ui.genres_bottom_sheet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    init {
        loadGenres()
    }

    private fun loadGenres() {
        viewModelScope.launch {
            _genres.postValue(genreRepository.getGenres())
        }
    }

    fun setSelectedGenre(genreName: String) {
        _selectedGenre.value = genreName
    }
}