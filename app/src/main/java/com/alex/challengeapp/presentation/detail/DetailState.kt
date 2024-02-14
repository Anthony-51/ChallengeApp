package com.alex.challengeapp.presentation.detail

import com.alex.challengeapp.domain.model.Movie

data class DetailState(
      val isLoading: Boolean = false,
      val movie: Movie = Movie(movieId = 0, title = "", overview = "", posterPath = "", backdropPath = "", releaseDate =  "", voteAverage = 0.0),
)
