package com.alex.challengeapp.domain.model

import android.os.Parcelable
import com.alex.challengeapp.data.remote.model.MovieDTO
import com.alex.challengeapp.util.Constants
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Movie(
      val id: Int,
      val title: String,
      val overview: String,
      val posterPath: String,
      val backdropPath: String,
      val releaseDate: String,
      val voteAverage: Double
): Parcelable

fun MovieDTO.toDomain() = Movie(
      id = id,
      title = title ?: "",
      overview = overview ?: "",
      posterPath = "${Constants.IMAGE_URL}$posterPath",
      backdropPath = "${Constants.IMAGE_URL}$backdropPath",
      releaseDate = releaseDate ?: "",
      voteAverage = voteAverage ?: 0.0
)
