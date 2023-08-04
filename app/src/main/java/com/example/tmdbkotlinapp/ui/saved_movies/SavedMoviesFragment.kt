package com.example.tmdbkotlinapp.ui.saved_movies

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tmdbkotlinapp.R

class SavedMoviesFragment : Fragment() {

    companion object {
        fun newInstance() = SavedMoviesFragment()
    }

    private lateinit var viewModel: SavedMoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_saved_movies, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SavedMoviesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}