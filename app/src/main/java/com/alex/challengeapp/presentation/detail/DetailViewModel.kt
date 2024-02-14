package com.alex.challengeapp.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.alex.challengeapp.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _detailState = MutableStateFlow(DetailState())
    val detailState = _detailState.asStateFlow()

    init {
        getMovie()
    }
    private fun getMovie() {
        val movie = savedStateHandle.get<Movie>("movie")
        movie?.let {
            _detailState.update {
                it.copy(movie = movie)
            }
        }
    }
}