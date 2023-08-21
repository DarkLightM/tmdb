package com.example.tmdbkotlinapp.ui.genres_bottom_sheet

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdbkotlinapp.MainApplication
import com.example.tmdbkotlinapp.databinding.FragmentGenreBottomSheetBinding
import com.example.tmdbkotlinapp.di.ViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import javax.inject.Inject

class GenreBottomSheet : BottomSheetDialogFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val genreBottomSheetViewModel by activityViewModels<GenreBottomSheetViewModel> { viewModelFactory }

    private lateinit var binding: FragmentGenreBottomSheetBinding

    private lateinit var genreRecycler: RecyclerView

    private lateinit var adapter: GenreBottomSheetCardAdapter

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

        genreRecycler = binding.genresRecyclerView
        val layoutManager = GridLayoutManager(requireContext(), 2)
        genreRecycler.layoutManager = layoutManager

        adapter = GenreBottomSheetCardAdapter { selectedGenreName ->
            genreBottomSheetViewModel.setSelectedGenre(selectedGenreName)
            dismiss()
        }

        genreBottomSheetViewModel.genres.observe(this) {
            adapter.submitList(it)
            genreRecycler.adapter = adapter

            binding.genreChoiceEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?, start: Int, count: Int, after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val filterText = s.toString().trim()
                    val filteredList = it.filter { genre ->
                        genre.name.contains(filterText, ignoreCase = true)
                    }
                    adapter.submitList(filteredList)
                    genreRecycler.adapter = adapter
                }

                override fun afterTextChanged(s: Editable?) {}
            })
        }
    }
}