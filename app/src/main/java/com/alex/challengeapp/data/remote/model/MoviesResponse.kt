package com.alex.challengeapp.data.remote.model

data class MoviesResponse(
      val page : Int,
      val totalPages : Int,
      val totalResults: Int,
      val results: List<MovieDTO>
)
