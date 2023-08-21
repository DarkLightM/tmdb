package com.example.tmdbkotlinapp.ui.saved_movies

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.tmdbkotlinapp.MainApplication
import com.example.tmdbkotlinapp.R
import com.example.tmdbkotlinapp.databinding.FragmentSavedMoviesBinding
import com.example.tmdbkotlinapp.di.ViewModelFactory
import com.example.tmdbkotlinapp.domain.models.Movie
import com.example.tmdbkotlinapp.ui.base.BaseFragment
import javax.inject.Inject

class SavedMoviesFragment : BaseFragment<SavedUiState, SavedEvent>(R.layout.fragment_saved_movies) {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override val viewModel by activityViewModels<SavedMoviesViewModel> { viewModelFactory }

    private var _binding: FragmentSavedMoviesBinding? = null

    private val binding get() = requireNotNull(_binding)

    override fun onAttach(context: Context) {
        MainApplication.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSavedMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun renderState(state: SavedUiState) {
        when (state) {
            is SavedUiState.Loading -> showLoading()
            is SavedUiState.Content -> showContent()
        }
    }

    override fun reactToSideEvent(event: SavedEvent) {
        super.reactToSideEvent(event)

        when (event) {
            is SavedEvent.ShowSavedMovies -> showSavedMovies(event.movies)
        }
    }

    private fun showSavedMovies(movies: List<Movie>) {
        val savedMoviesRecycler = binding.savedMoviesRecycler
        val savedMoviesAdapter = SavedMoviesAdapter()
        savedMoviesAdapter.submitList(movies)
        savedMoviesRecycler.adapter = savedMoviesAdapter
    }

    private fun showLoading() {
       /* binding.progressBar.isVisible = true
        binding.savedMoviesRecycler.isInvisible = true*/
    }

    private fun showContent() {
       /* binding.progressBar.isInvisible = true
        binding.savedMoviesRecycler.isVisible = true*/
    }

}