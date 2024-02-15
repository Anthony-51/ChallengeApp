package com.alex.challengeapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.alex.challengeapp.data.Response
import com.alex.challengeapp.data.local.MovieMediator
import com.alex.challengeapp.data.local.dao.MovieDao
import com.alex.challengeapp.data.local.entity.MovieEntity
import com.alex.challengeapp.data.remote.model.MoviesResponse
import com.alex.challengeapp.data.repository.data_source.MovieDataSource
import com.alex.challengeapp.domain.repository.MovieRepository
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class MovieRepositoryImpl @Inject constructor(
      private val service: MovieDataSource,
      private val movieDao: MovieDao,
      private val movieMediator: MovieMediator
): MovieRepository{
      override suspend fun getMoviesByPage(page: Int): Response<MoviesResponse> {
            return service.getMoviesByPage(page)
      }

      override fun getMoviePager(): Pager<Int, MovieEntity> {
            return Pager(
                  config = PagingConfig(pageSize = 20),
                  remoteMediator = movieMediator,
                  pagingSourceFactory = { movieDao.pagingSource() }
            )
      }
}