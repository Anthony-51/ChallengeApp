package com.alex.challengeapp.domain.model

import com.alex.challengeapp.data.remote.model.MovieDTO

data class Movie(
      val id: Int,
      val title: String,
      val overview: String,
      val posterPath: String,
      val backdropPath: String,
      val releaseDate: String,
      val voteAverage: Double
)

fun MovieDTO.toDomain() = Movie(
      id = id,
      title = title ?: "",
      overview = overview ?: "",
      posterPath = posterPath ?: "",
      backdropPath = backdropPath ?: "",
      releaseDate = releaseDate ?: "",
      voteAverage = voteAverage ?: 0.0
)
