package com.example.tmdbkotlinapp.ui.movie_details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import coil.load
import com.example.tmdbkotlinapp.MainApplication
import com.example.tmdbkotlinapp.R
import com.example.tmdbkotlinapp.data.repository.DataSource
import com.example.tmdbkotlinapp.databinding.FragmentMovieDetailsBinding
import com.example.tmdbkotlinapp.di.ViewModelFactory
import com.example.tmdbkotlinapp.domain.models.Actor
import com.example.tmdbkotlinapp.domain.models.Genre
import com.example.tmdbkotlinapp.domain.models.Movie
import com.example.tmdbkotlinapp.ui.base.BaseFragment
import javax.inject.Inject

class MovieDetailsFragment :
    BaseFragment<DetailUiState, DetailEvent>(R.layout.fragment_movie_details) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override val viewModel by activityViewModels<MovieDetailsViewModel> { viewModelFactory }

    private var _binding: FragmentMovieDetailsBinding? = null

    private val binding get() = requireNotNull(_binding)

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
            val source = this.arguments?.getSerializable("source", DataSource::class.java)
            viewModel.loadMovieDetails(it, requireNotNull(source))
        }

        setButtons()
    }

    override fun renderState(state: DetailUiState) {
        when (state) {
            is DetailUiState.Loading -> showLoading()
            is DetailUiState.Content -> showContent()
        }
    }

    override fun reactToSideEvent(event: DetailEvent) {
        super.reactToSideEvent(event)

        when (event) {
            is DetailEvent.ShowDetails -> {
                setStatic(event.movie)
                setCastRecycler(event.cast)
                event.movie.genreList?.let { setGenreRecycler(it) }
            }
        }
    }

    private fun setStatic(movie: Movie) {
        binding.movieBcgPoster.load(movie.posterPath)
        binding.movieName.text = movie.originalTitle
        binding.movieRating.text = movie.rating.toString()
        binding.movieReleaseDate.text = movie.releaseDate
        binding.movieDescription.text = movie.overview
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

    private fun setButtons() {
        binding.backArrow.setOnClickListener {
            val navController = it.findNavController()
            navController.navigateUp()
        }

        /*binding.likeButton.setOnClickListener {
            movieDetailsViewModel.saveMovieInDb()
        }*/
    }

    private fun showContent() {
        with(binding){
            movieName.isVisible = true
            movieReleaseDate.isVisible = true
            movieRating.isVisible = true
            movieDescription.isVisible = true
            castRecyclerView.isVisible = true
            genreRecyclerView.isVisible = true
            progressBar.isInvisible = true
        }
    }

    private fun showLoading() {
        with(binding){
            movieName.isInvisible = true
            movieReleaseDate.isInvisible = true
            movieRating.isInvisible = true
            movieDescription.isInvisible = true
            castRecyclerView.isInvisible = true
            genreRecyclerView.isInvisible = true
            progressBar.isVisible = true
        }
    }
}