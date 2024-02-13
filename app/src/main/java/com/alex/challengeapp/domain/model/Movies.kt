package com.alex.challengeapp.domain.model

import com.alex.challengeapp.data.remote.model.MoviesResponse

data class Movies(
      val page: Int,
      val results: List<Movie>,
      val totalPages: Int,
      val totalResults: Int
)

fun MoviesResponse.toDomain() = Movies(
      page = page,
      results = results.map { it.toDomain() },
      totalPages = totalPages,
      totalResults = totalResults
)
