package com.example.tmdbkotlinapp.ui.random_movie

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.tmdbkotlinapp.MainApplication
import com.example.tmdbkotlinapp.api.repository.cast.CastRepository
import com.example.tmdbkotlinapp.api.repository.movie.MovieRepository
import com.example.tmdbkotlinapp.databinding.FragmentRandomMovieBinding
import com.example.tmdbkotlinapp.di.ViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

class RandomMovieFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val randomMovieViewModel by viewModels<RandomMovieViewModel> { viewModelFactory }

    private lateinit var binding: FragmentRandomMovieBinding

    override fun onAttach(context: Context) {
        MainApplication.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRandomMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}