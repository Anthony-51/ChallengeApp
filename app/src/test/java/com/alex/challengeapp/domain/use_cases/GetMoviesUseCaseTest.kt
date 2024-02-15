package com.alex.challengeapp.domain.use_cases

import app.cash.turbine.test
import com.alex.challengeapp.data.Response
import com.alex.challengeapp.data.remote.model.MovieDTO
import com.alex.challengeapp.data.remote.model.MoviesResponse
import com.alex.challengeapp.domain.model.Movies
import com.alex.challengeapp.domain.model.ResourceState
import com.alex.challengeapp.domain.model.toDomain
import com.alex.challengeapp.domain.repository.MovieRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetMoviesUseCaseTest {

      private val mockMovies = (0..10).map {
            MovieDTO(
                  id = it,
                  title = "Movie $it",
                  overview = "Overview $it",
                  posterPath = "posterPath $it",
                  backdropPath = "backdropPath $it",
                  releaseDate = "releaseDate $it",
                  voteAverage = it.toDouble()
            )
      }
      @RelaxedMockK
      private lateinit var repository: MovieRepository

      lateinit var useCase: GetMoviesUseCase

      @Before
      fun setUp() {
            Dispatchers.setMain(Dispatchers.IO)
            MockKAnnotations.init(this)
            useCase = GetMoviesUseCase(repository)
      }

      @After
      fun tearDown() {
            Dispatchers.resetMain()
      }
      @Test
      fun `verify when the result of api is success`() = runBlocking {
            coEvery { repository.getMoviesByPage(1) } returns Response.Success(MoviesResponse(page = 1, results = emptyList(), totalPages = 1, totalResults = 0))

            useCase(1).test {
                  val emission = awaitItem()
                  assertEquals(emission, ResourceState.Loading)
                  val emission2 = awaitItem()
                  assertEquals(emission2, ResourceState.Success(Movies(page=1, results= emptyList(), totalPages=1, totalResults=0)))
                  cancelAndConsumeRemainingEvents()
            }
      }

      @Test
      fun `verify when the result of api is error`() = runBlocking {
            coEvery { repository.getMoviesByPage(1) } returns Response.Error("Error", 0)

            useCase(1).test {
                  val emission = awaitItem()
                  assertEquals(emission, ResourceState.Loading)
                  val emission2 = awaitItem()
                  assertEquals(emission2, ResourceState.Error("Error", 0))
                  cancelAndConsumeRemainingEvents()
            }
      }

      @Test
      fun `verify if movies result is correctly converted to domain`() = runBlocking {
            coEvery { repository.getMoviesByPage(1) } returns Response.Success(MoviesResponse(page = 1, results = mockMovies, totalPages = 1, totalResults = 0))

            useCase(1).test {
                  val emission = awaitItem()
                  assertEquals(emission, ResourceState.Loading)
                  val emission2 = awaitItem()
                  assertEquals((emission2 as ResourceState.Success).data.results, mockMovies.map { it.toDomain() })
                  cancelAndConsumeRemainingEvents()
            }
      }
}