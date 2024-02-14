package com.alex.challengeapp.presentation.home

import com.alex.challengeapp.domain.model.Movie

interface HomeEvent {
    object OnLoadMoreMovies : HomeEvent

    data class OnMovieClick(val movie: Movie) : HomeEvent
}