package com.alex.challengeapp.domain.repository

import com.alex.challengeapp.data.Response
import com.alex.challengeapp.data.remote.model.MoviesResponse

interface MovieRepository {

      suspend fun getMoviesByPage(page: Int): Response<MoviesResponse>
}