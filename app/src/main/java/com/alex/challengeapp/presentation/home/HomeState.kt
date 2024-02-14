package com.alex.challengeapp.presentation.home

import com.alex.challengeapp.domain.model.Movie

data class HomeState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val currentPage: Int = 1,
    val isLastPage: Boolean = false
)
