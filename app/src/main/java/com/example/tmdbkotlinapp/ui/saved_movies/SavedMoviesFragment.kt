package com.example.tmdbkotlinapp.ui.saved_movies

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.tmdbkotlinapp.MainApplication
import com.example.tmdbkotlinapp.databinding.FragmentSavedMoviesBinding
import com.example.tmdbkotlinapp.di.ViewModelFactory
import javax.inject.Inject

class SavedMoviesFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val savedMoviesViewModel by viewModels<SavedMoviesViewModel> { viewModelFactory }

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

        savedMoviesViewModel.movieList.observe(viewLifecycleOwner){
            val savedMoviesRecycler = binding.savedMoviesRecycler
            val savedMoviesAdapter = SavedMoviesAdapter()
            savedMoviesAdapter.submitList(it)
            savedMoviesRecycler.adapter = savedMoviesAdapter
        }
    }

}