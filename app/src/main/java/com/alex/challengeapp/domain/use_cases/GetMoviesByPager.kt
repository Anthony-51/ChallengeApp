package com.alex.challengeapp.domain.use_cases

import androidx.paging.cachedIn
import androidx.paging.map
import com.alex.challengeapp.domain.model.toDomain
import com.alex.challengeapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMoviesByPager @Inject constructor(
      private val repository: MovieRepository
){
      operator fun invoke() = repository.getMoviePager().flow.map { pagingData ->
            pagingData.map { it.toDomain() }
      }
}