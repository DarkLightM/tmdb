package com.example.tmdbkotlinapp.ui.movie_details

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.tmdbkotlinapp.MainApplication
import com.example.tmdbkotlinapp.R
import com.example.tmdbkotlinapp.databinding.FragmentMovieDetailsBinding
import com.example.tmdbkotlinapp.databinding.FragmentPopularMoviesBinding
import com.example.tmdbkotlinapp.di.ViewModelFactory
import com.example.tmdbkotlinapp.ui.popular_movies.PopularMoviesViewModel
import javax.inject.Inject

class MovieDetailsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val movieDetailsViewModel by viewModels<MovieDetailsViewModel> { viewModelFactory }

    private var _binding: FragmentMovieDetailsBinding? = null

    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        MainApplication.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieDetailsViewModel.getMovieDetails(11)
        movieDetailsViewModel.getMovieCast(11)

        movieDetailsViewModel.movie.observe(viewLifecycleOwner){
            Log.i("Details fragment", it.toString())
        }

        movieDetailsViewModel.cast.observe(viewLifecycleOwner){
            Log.i("Details fragment", it.toString())
        }
    }

}