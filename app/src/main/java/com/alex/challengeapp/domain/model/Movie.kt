package com.alex.challengeapp.domain.model

import android.os.Parcelable
import com.alex.challengeapp.data.local.entity.MovieEntity
import com.alex.challengeapp.data.remote.model.MovieDTO
import com.alex.challengeapp.util.Constants
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import java.util.UUID

@Parcelize
@Serializable
data class Movie(
      val id: String = UUID.randomUUID().toString(),
      val movieId: Int,
      val title: String,
      val overview: String,
      val posterPath: String,
      val backdropPath: String,
      val releaseDate: String,
      val voteAverage: Double
): Parcelable

fun MovieDTO.toDomain() = Movie(
      id = uuid,
      movieId = id,
      title = title ?: "",
      overview = overview ?: "",
      posterPath = "${Constants.IMAGE_URL}$posterPath",
      backdropPath = "${Constants.IMAGE_URL}$backdropPath",
      releaseDate = releaseDate ?: "",
      voteAverage = voteAverage ?: 0.0
)

fun MovieDTO.toEntity() = MovieEntity(
      id = id,
      title = title?: "",
      overview = overview?: "",
      posterPath = posterPath?: "",
      backdropPath = backdropPath?: "",
      releaseDate = releaseDate?: "",
      voteAverage = voteAverage?: 0.0
)

fun MovieEntity.toDomain() = Movie(
      movieId = id,
      title = title,
      overview = overview,
      posterPath = "${Constants.IMAGE_URL}$posterPath",
      backdropPath = "${Constants.IMAGE_URL}$backdropPath",
      releaseDate = releaseDate,
      voteAverage = voteAverage
)


