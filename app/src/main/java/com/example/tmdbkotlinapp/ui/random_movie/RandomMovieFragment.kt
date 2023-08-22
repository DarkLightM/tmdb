package com.example.tmdbkotlinapp.ui.random_movie

import android.content.Context
import android.icu.util.Calendar
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.tmdbkotlinapp.MainApplication
import com.example.tmdbkotlinapp.R
import com.example.tmdbkotlinapp.databinding.FragmentRandomMovieBinding
import com.example.tmdbkotlinapp.di.ViewModelFactory
import com.example.tmdbkotlinapp.domain.models.Movie
import com.example.tmdbkotlinapp.ui.base.BaseFragment
import com.example.tmdbkotlinapp.ui.genres_bottom_sheet.GenreBottomSheet
import com.example.tmdbkotlinapp.ui.genres_bottom_sheet.GenreBottomSheetViewModel
import javax.inject.Inject

class RandomMovieFragment :
    BaseFragment<RandomUiState, RandomEvent>(R.layout.fragment_random_movie) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override val viewModel by activityViewModels<RandomMovieViewModel> { viewModelFactory }

    private val genreBottomSheetViewModel by activityViewModels<GenreBottomSheetViewModel> { viewModelFactory }

    private lateinit var binding: FragmentRandomMovieBinding

    override fun onAttach(context: Context) {
        MainApplication.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentRandomMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validateInput(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        }

        binding.editYear.addTextChangedListener(textWatcher)

        binding.editGenreCardView.setOnClickListener {
            GenreBottomSheet().show(parentFragmentManager, "genreTag")
        }

        genreBottomSheetViewModel.selectedGenre.observe(viewLifecycleOwner) {
            binding.selectedGenre.text = it
        }

        binding.getRandomFilmButton.setOnClickListener {
            if (validateInput(binding.editYear.text.toString()) && binding.selectedGenre.text != null) {
                viewModel.getRandomMovie(
                    binding.editYear.text.toString().toInt(), binding.selectedGenre.text.toString()
                )
            }
        }
    }

    override fun renderState(state: RandomUiState) {
        when (state) {
            is RandomUiState.Loading -> showLoading()
            is RandomUiState.Content -> showContent()
        }
    }

    override fun reactToSideEvent(event: RandomEvent) {
        super.reactToSideEvent(event)

        when (event) {
            is RandomEvent.GoToDetail -> goToDetail(event.movie)
            is RandomEvent.SendErrorToast -> Toast.makeText(
                this.context,
                event.errorText,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun showLoading() {
        with(binding) {
            editYearCardView.isInvisible = true
            getRandomFilmButton.isInvisible = true
            editGenreCardView.isInvisible = true
            progressBar.isVisible = true
        }
    }

    private fun showContent() {
        with(binding) {
            editYearCardView.isVisible = true
            getRandomFilmButton.isVisible = true
            editGenreCardView.isVisible = true
            progressBar.isInvisible = true
        }
    }

    private fun goToDetail(movie: Movie) {
        val bundle = Bundle()
        bundle.putInt("movieId", movie.movieId)
        bundle.putInt("movieRemoteId", movie.movieRemoteId)
        val navController = view?.findNavController()
        navController?.navigate(
            R.id.action_randomMovieFragment_to_movieDetailsFragment, bundle
        )
    }

    private fun validateInput(input: String): Boolean {
        if (input.isNotEmpty()) {
            val currentYear = Calendar.getInstance().get(Calendar.YEAR)
            val inputValue = input.toIntOrNull()

            return if (inputValue == null) {
                binding.editYear.error = requireActivity().getString(R.string.year_type_error)
                false
            } else if (inputValue < 1900 || inputValue > currentYear) {
                binding.editYear.error = String.format(
                    requireActivity().getString((R.string.year_number_error)), currentYear
                )
                false
            } else {
                binding.editYear.error = null
                true
            }
        } else {
            binding.editYear.error = null
        }
        return false
    }
}