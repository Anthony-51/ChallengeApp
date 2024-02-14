package com.alex.challengeapp.presentation.detail

import com.alex.challengeapp.domain.model.Movie

data class DetailState(
    val isLoading: Boolean = false,
    val movie: Movie = Movie(0, "", "", "", "", "", 0.0),
)
