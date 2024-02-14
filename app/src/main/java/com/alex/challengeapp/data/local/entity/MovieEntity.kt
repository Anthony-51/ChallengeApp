package com.alex.challengeapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
      tableName = "movie"
)
data class MovieEntity(
      @PrimaryKey(autoGenerate = false)
      val id: Int,
      val title: String,
      val overview: String,
      @ColumnInfo(name = "poster_path")
      val posterPath: String,
      @ColumnInfo(name = "backdrop_path")
      val backdropPath: String,
      @ColumnInfo(name = "release_date")
      val releaseDate: String,
      @ColumnInfo(name = "vote_average")
      val voteAverage: Double
)
