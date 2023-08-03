package com.example.tmdbkotlinapp.ui.popular_movies

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.tmdbkotlinapp.MainApplication
import com.example.tmdbkotlinapp.R
import com.example.tmdbkotlinapp.databinding.FragmentPopularMoviesBinding
import com.example.tmdbkotlinapp.databinding.FragmentRandomMovieBinding
import com.example.tmdbkotlinapp.di.ViewModelFactory
import com.example.tmdbkotlinapp.domain.models.Movie
import com.example.tmdbkotlinapp.ui.random_movie.RandomMovieViewModel
import javax.inject.Inject

class PopularMoviesFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val popularMoviesViewModel by viewModels<PopularMoviesViewModel> { viewModelFactory }

    private lateinit var binding: FragmentPopularMoviesBinding

    override fun onAttach(context: Context) {
        MainApplication.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentPopularMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        popularMoviesViewModel.popularMovieList.observe(viewLifecycleOwner) {
            createRecyclerView(it)
        }
    }

    private fun createRecyclerView(popularMovies: List<Movie>) {
        requireActivity().runOnUiThread {
            val popularMovieRecyclerView = binding.popularMoviesRecyclerView
            val popularMoviesAdapter = PopularMoviesAdapter()
            popularMoviesAdapter.submitList(popularMovies)
            popularMovieRecyclerView.adapter = popularMoviesAdapter
        }
    }

}