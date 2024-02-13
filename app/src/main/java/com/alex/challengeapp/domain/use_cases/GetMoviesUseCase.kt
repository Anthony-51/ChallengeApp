package com.alex.challengeapp.domain.use_cases

import com.alex.challengeapp.data.Response
import com.alex.challengeapp.domain.model.ResourceState
import com.alex.challengeapp.domain.model.toDomain
import com.alex.challengeapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
      private val repository: MovieRepository
){
      operator fun invoke(page: Int) = flow {
            emit(ResourceState.Loading)
            when (val response = repository.getMoviesByPage(page)) {
                  is Response.Success -> emit(ResourceState.Success(response.data.toDomain()))
                  is Response.Error -> emit(ResourceState.Error(response.msg, response.code))
            }
      }
}