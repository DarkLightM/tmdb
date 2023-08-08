package com.example.tmdbkotlinapp.ui.popular_movies

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.tmdbkotlinapp.MainApplication
import com.example.tmdbkotlinapp.databinding.FragmentPopularMoviesBinding
import com.example.tmdbkotlinapp.di.ViewModelFactory
import com.example.tmdbkotlinapp.domain.models.Movie
import javax.inject.Inject

class PopularMoviesFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val popularMoviesViewModel by viewModels<PopularMoviesViewModel> { viewModelFactory }

    private var _binding: FragmentPopularMoviesBinding? = null

    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        MainApplication.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPopularMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*popularMoviesViewModel.popularMovieList.observe(viewLifecycleOwner) {
            createRecyclerView(it)
        }*/
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