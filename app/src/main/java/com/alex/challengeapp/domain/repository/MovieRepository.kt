package com.alex.challengeapp.domain.repository

import androidx.paging.Pager
import com.alex.challengeapp.data.Response
import com.alex.challengeapp.data.local.entity.MovieEntity
import com.alex.challengeapp.data.remote.model.MoviesResponse

interface MovieRepository {

      suspend fun getMoviesByPage(page: Int): Response<MoviesResponse>

      fun getMoviePager(): Pager<Int, MovieEntity>
}