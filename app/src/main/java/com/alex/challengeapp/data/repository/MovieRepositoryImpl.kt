package com.alex.challengeapp.data.repository

import com.alex.challengeapp.data.Response
import com.alex.challengeapp.data.remote.model.MoviesResponse
import com.alex.challengeapp.data.repository.data_source.MovieDataSource
import com.alex.challengeapp.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
      private val service: MovieDataSource
): MovieRepository{
      override suspend fun getMoviesByPage(page: Int): Response<MoviesResponse> {
            return service.getMoviesByPage(page)
      }
}