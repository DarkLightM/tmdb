package com.example.tmdbkotlinapp.ui.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow

abstract class BaseFragment<S : UiState, E : Event> (@LayoutRes layoutId: Int): Fragment(layoutId) {
    protected abstract val viewModel: BaseViewModel<S, E>

    protected open fun renderState(state: S) = Unit

    protected open fun reactToSideEvent(event: E) = Unit

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeFlow(viewModel.state, ::renderState)
        observeFlow(viewModel.events, ::reactToSideEvent)
    }

    fun <T> Fragment.observeFlow(flow: Flow<T>, action: (T) -> Unit) {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            flow.collect { action(it) }
        }
    }
}