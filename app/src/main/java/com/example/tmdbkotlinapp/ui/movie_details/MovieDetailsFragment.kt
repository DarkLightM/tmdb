package com.example.tmdbkotlinapp.ui.movie_details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import coil.load
import com.example.tmdbkotlinapp.MainApplication
import com.example.tmdbkotlinapp.R
import com.example.tmdbkotlinapp.databinding.FragmentMovieDetailsBinding
import com.example.tmdbkotlinapp.di.ViewModelFactory
import com.example.tmdbkotlinapp.domain.models.Actor
import com.example.tmdbkotlinapp.domain.models.Genre
import com.example.tmdbkotlinapp.domain.models.Movie
import com.example.tmdbkotlinapp.ui.base.BaseFragment
import com.example.tmdbkotlinapp.ui.base.Event
import javax.inject.Inject

class MovieDetailsFragment :
    BaseFragment<DetailUiState, Event>(R.layout.fragment_movie_details) {

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

        val movieRemoteId = this.arguments?.getInt("movieRemoteId") ?: -1
        viewModel.loadMovieDetails(movieRemoteId)
    }

    override fun renderState(state: DetailUiState) {
        when (state) {
            is DetailUiState.Loading -> changeVisibility(View.INVISIBLE)
            is DetailUiState.Content -> {
                setButtons(state.isSaved)
                setStatic(state.movie)
                setCastRecycler(state.movie.cast ?: emptyList())
                setGenreRecycler(state.movie.genreList ?: emptyList())
                changeVisibility(View.VISIBLE)
            }

            is DetailUiState.Error -> showError()
        }
    }

    private fun setStatic(movie: Movie) {
        binding.movieBcgPoster.load(movie.posterPath) {
            placeholder(R.drawable.ic_placeholder)
            listener(onSuccess = { _, _ ->
                binding.movieBcgPoster.scaleType = ImageView.ScaleType.CENTER_CROP
            })
        }
        if (movie.isAdult) {
            binding.isAdultIcon.isVisible = true
        }
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

    private fun setButtons(isSaved: Boolean) {
        binding.backArrow.setOnClickListener {
            val navController = it.findNavController()
            navController.navigateUp()
        }
        when (isSaved) {
            true -> {
                binding.likeBcg.setCardBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(), R.color.dark_blue
                    )
                )
                binding.likeIcon.setImageResource(R.drawable.ic_like_tapped)
                binding.likeBcg.setOnClickListener {
                    viewModel.deleteMovieFromDb()
                }
            }

            false -> {
                binding.likeBcg.setCardBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(), R.color.white
                    )
                )
                binding.likeIcon.setImageResource(R.drawable.ic_like_untapped)
                binding.likeBcg.setOnClickListener {
                    viewModel.saveMovieInDb()
                }
            }
        }
    }

    private fun changeVisibility(visibility: Int) {
        with(binding) {
            movieName.visibility = visibility
            movieReleaseDate.visibility = visibility
            movieRating.visibility = visibility
            ratingBcg.visibility = visibility
            likeBcg.visibility = visibility
            likeIcon.visibility = visibility
            movieDescription.visibility = visibility
            castRecyclerView.visibility = visibility
            genreRecyclerView.visibility = visibility
            castHeader.visibility = visibility
            genreHeader.visibility = visibility
            movieBcgPoster.visibility = visibility
            movieNameCard.visibility = visibility
            movieDescriptionBcg.visibility = visibility

            if (visibility == View.VISIBLE) {
                progressBar.visibility = View.INVISIBLE
            } else {
                progressBar.visibility = View.VISIBLE
            }
        }
    }

    private fun showError() {
        changeVisibility(View.INVISIBLE)
        binding.progressBar.visibility = View.INVISIBLE
        binding.errorLayout.root.visibility = View.VISIBLE

        binding.errorLayout.retryButton.setOnClickListener {
            viewModel.loadMovieDetails(this.arguments?.getInt("movieRemoteId") ?: -1)
            binding.errorLayout.root.visibility = View.INVISIBLE
        }
    }
}
