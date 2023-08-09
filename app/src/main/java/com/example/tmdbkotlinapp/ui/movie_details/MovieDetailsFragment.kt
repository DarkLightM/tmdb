package com.example.tmdbkotlinapp.ui.movie_details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import coil.load
import com.example.tmdbkotlinapp.MainApplication
import com.example.tmdbkotlinapp.databinding.FragmentMovieDetailsBinding
import com.example.tmdbkotlinapp.di.ViewModelFactory
import com.example.tmdbkotlinapp.domain.models.Actor
import com.example.tmdbkotlinapp.domain.models.Genre
import com.example.tmdbkotlinapp.domain.models.Movie
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

        val movieId = this.arguments?.getInt("movieId")
        movieId?.let {
            movieDetailsViewModel.getMovieDetails(it)
            movieDetailsViewModel.getMovieCast(it)
        }

        movieDetailsViewModel.movie.observe(viewLifecycleOwner) { movie ->
            setStatic(movie)
            movie.genreList?.let { genreList -> setGenreRecycler(genreList) }
        }

        movieDetailsViewModel.cast.observe(viewLifecycleOwner) {
            setCastRecycler(it)
        }
    }

    private fun setStatic(movie: Movie) {
        binding.movieBcgPoster.load(movie.posterPath)
        binding.movieName.text = movie.originalTitle
        binding.movieRating.text = movie.rating.toString()
        binding.movieReleaseDate.text = movie.releaseDate
        binding.movieDescription.text = movie.overview

        binding.backArrow.setOnClickListener{
            val navController = it.findNavController()
            navController.navigateUp()
        }
    }

    private fun setCastRecycler(cast: List<Actor>) {
        val castRecycler = binding.castRecyclerView
        val adapter = ActorCardAdapter()
        adapter.submitList(cast)
        castRecycler.adapter = adapter
    }

    private fun setGenreRecycler(genres: List<Genre>) {
        val genreRecycler = binding.genreRecyclerView
        val adapter = GenreCardAdapter()
        adapter.submitList(genres)
        genreRecycler.adapter = adapter
    }
}