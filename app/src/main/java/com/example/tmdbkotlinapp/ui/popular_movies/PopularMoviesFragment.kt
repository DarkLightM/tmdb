package com.example.tmdbkotlinapp.ui.popular_movies

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.paging.PagingData
import com.example.tmdbkotlinapp.MainApplication
import com.example.tmdbkotlinapp.R
import com.example.tmdbkotlinapp.databinding.FragmentPopularMoviesBinding
import com.example.tmdbkotlinapp.di.ViewModelFactory
import com.example.tmdbkotlinapp.domain.models.Movie
import com.example.tmdbkotlinapp.ui.base.BaseFragment
import javax.inject.Inject

class PopularMoviesFragment : BaseFragment<PopularUiState, PopularEvent>(R.layout.fragment_popular_movies) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override val viewModel by activityViewModels<PopularMoviesViewModel> { viewModelFactory }

    private var _binding: FragmentPopularMoviesBinding? = null

    private val binding get() = requireNotNull(_binding)

    private var adapter: PopularMoviesAdapter? = null

    override fun onAttach(context: Context) {
        MainApplication.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPopularMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun renderState(state: PopularUiState) {
        when (state) {
            is PopularUiState.Loading -> {}
            is PopularUiState.Content -> showMovies(state.pagingData)
        }
    }

    override fun reactToSideEvent(event: PopularEvent) {
        super.reactToSideEvent(event)

        when(event){
            is PopularEvent.ShowMovie -> showMovies(event.pagingData)
        }
    }

    private fun showMovies(pagingData: PagingData<Movie>){
        adapter = PopularMoviesAdapter()
        binding.popularMoviesRecyclerView.adapter = adapter
        adapter?.submitData(viewLifecycleOwner.lifecycle, pagingData)
    }
}