package com.alex.challengeapp.data.remote.model

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class MovieDTO(
      val uuid: String = UUID.randomUUID().toString(),
      @SerializedName("id")
      val id: Int,
      @SerializedName("title")
      val title: String? = null,
      @SerializedName("adult")
      val adult: Boolean? = null,
      @SerializedName("overview")
      val overview: String? = null,
      @SerializedName("backdrop_path")
      val backdropPath: String? = null,
      @SerializedName("poster_path")
      val posterPath: String? = null,
      @SerializedName("release_date")
      val releaseDate: String? = null,
      @SerializedName("popularity")
      val popularity: Double? = null,
      @SerializedName("vote_average")
      val voteAverage: Double? = null,
      @SerializedName("vote_count")
      val voteCount: Double? = null,
      @SerializedName("genre_ids")
      val genreIds: List<Int>? = null
)
