package com.alex.challengeapp.data.local

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.alex.challengeapp.data.local.entity.MovieEntity
import com.alex.challengeapp.data.remote.MovieApi
import com.alex.challengeapp.domain.model.toEntity
import com.alex.challengeapp.util.Constants
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class MovieMediator @Inject constructor(
      private val movieApi: MovieApi,
      private val db: MovieDB
): RemoteMediator<Int, MovieEntity>() {
      override suspend fun load(
            loadType: LoadType,
            state: PagingState<Int, MovieEntity>
      ): MediatorResult {
            return try {
                  val loadKey = when(loadType) {
                        LoadType.REFRESH -> {
                              1
                        }
                        LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                        LoadType.APPEND -> {
                              val lastItem = state.lastItemOrNull()

                              if(lastItem == null) {
                                    1
                              } else {
                                    val count = db.movieDao().getCount()
                                    (count / state.config.pageSize) + 1
                              }
                        }
                  }
                  delay(2000)
                  val response = movieApi.getMovies(loadKey, Constants.API_KEY)
                  val movies = response.results
                  if(movies.isNotEmpty()) {
                        db.withTransaction {
                              if(loadType == LoadType.REFRESH) {
                                    db.movieDao().deleteAll()
                              }
                              db.movieDao().insertAll(movies.map { it.toEntity() })
                        }
                  }
                  MediatorResult.Success(endOfPaginationReached = response.page == response.totalPages)
            }  catch(e: IOException) {
                  MediatorResult.Error(e)
            } catch(e: HttpException) {
                  MediatorResult.Error(e)
            }
      }

}