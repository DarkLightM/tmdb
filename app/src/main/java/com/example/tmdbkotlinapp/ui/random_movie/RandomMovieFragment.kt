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
import com.example.tmdbkotlinapp.api.repository.cast.ICastRepository
import com.example.tmdbkotlinapp.api.repository.movie.IMovieRepository
import com.example.tmdbkotlinapp.databinding.FragmentRandomMovieBinding
import com.example.tmdbkotlinapp.di.ViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

class RandomMovieFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var iMovieRepository: IMovieRepository

    @Inject
    lateinit var iCastRepository: ICastRepository

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

        /*binding.getRandomFilmButton.setOnClickListener() {
            randomMovieViewModel.getRandomMovie(
                (binding.editYear.text).toString().toInt(),
                binding.editGenre.toString()
            )
        }

        randomMovieViewModel.movie.observe(viewLifecycleOwner){
            Log.i("Movie", it.toString())
        }*/
        /*lifecycleScope.launch {
            Log.i(
                "Random movie list",
                iMovieRepository.getRandomMovieList(1, 2000, "Action")[0].toString()
            )
            Log.i("Popular movie list", iMovieRepository.getPopularMovieList()[0].toString())
            Log.i("Movie details", iMovieRepository.getMovieDetails(11).toString())
            Log.i("Total pages", iMovieRepository.getTotalPages(2000, "Action").toString())
            Log.i("Movie cast", iCastRepository.getMovieCast(11)[0].toString())
        }*/
    }

}