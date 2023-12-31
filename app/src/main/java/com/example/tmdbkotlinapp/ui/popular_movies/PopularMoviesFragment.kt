package com.example.tmdbkotlinapp.ui.popular_movies

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.tmdbkotlinapp.MainApplication
import com.example.tmdbkotlinapp.R
import com.example.tmdbkotlinapp.databinding.FragmentPopularMoviesBinding
import com.example.tmdbkotlinapp.di.ViewModelFactory
import com.example.tmdbkotlinapp.domain.models.Movie
import com.example.tmdbkotlinapp.ui.base.BaseFragment
import com.example.tmdbkotlinapp.ui.base.Event
import javax.inject.Inject

class PopularMoviesFragment :
    BaseFragment<PopularUiState, Event>(R.layout.fragment_popular_movies) {

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
            is PopularUiState.Loading -> changeVisibility(View.GONE)
            is PopularUiState.Content -> {
                showMovies(state.pagingData)
                changeVisibility(View.VISIBLE)
            }
        }
    }

    private fun showMovies(pagingData: PagingData<Movie>) {
        adapter = PopularMoviesAdapter()
        adapter?.submitData(viewLifecycleOwner.lifecycle, pagingData)

        val popularMoviesRecyclerView = binding.popularMoviesRecyclerView
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        popularMoviesRecyclerView.layoutManager = layoutManager
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(popularMoviesRecyclerView)
        popularMoviesRecyclerView.adapter = adapter
    }

    private fun changeVisibility(visibility: Int) {
        binding.popularMoviesRecyclerView.visibility = visibility
        binding.progressBar.visibility = if (visibility == View.VISIBLE) View.GONE else View.VISIBLE

    }
}