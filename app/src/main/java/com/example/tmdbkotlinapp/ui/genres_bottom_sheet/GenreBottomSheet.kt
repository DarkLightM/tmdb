package com.example.tmdbkotlinapp.ui.genres_bottom_sheet

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import com.example.tmdbkotlinapp.MainApplication
import com.example.tmdbkotlinapp.R
import com.example.tmdbkotlinapp.databinding.FragmentGenreBottomSheetBinding
import com.example.tmdbkotlinapp.di.ViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import javax.inject.Inject

class GenreBottomSheet : BottomSheetDialogFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val genreBottomSheetViewModel by activityViewModels<GenreBottomSheetViewModel> { viewModelFactory }

    private lateinit var binding: FragmentGenreBottomSheetBinding

    override fun onAttach(context: Context) {
        MainApplication.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentGenreBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        genreBottomSheetViewModel.error.observe(this) {
            when (it) {
                true -> binding.downloadError.isVisible = true
                false -> binding.downloadError.isVisible = false
            }
        }

        genreBottomSheetViewModel.genres.observe(this) { genres ->
            for (genre in genres) {
                val chip = createChip(genre.name)
                binding.genresChipGroup.addView(chip)
            }
            binding.genreChoiceEditText.addTextChangedListener { text ->
                val filterText = text.toString().trim()
                binding.genresChipGroup.removeAllViews()
                for (genre in genres) {
                    if (genre.name.contains(filterText, ignoreCase = true)) {
                        val chip = createChip(genre.name)
                        binding.genresChipGroup.addView(chip)
                    }
                }
            }
        }
    }

    private fun createChip(text: String): Chip {
        val chip = Chip(requireContext())
        chip.text = text
        chip.isCheckable = true
        chip.setChipBackgroundColorResource(R.color.dark_blue)
        chip.setTextAppearance(R.style.description_style)
        chip.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                genreBottomSheetViewModel.setSelectedGenre(text)
                dismiss()
            }
        }
        return chip
    }
}