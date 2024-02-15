package com.alex.challengeapp.data.local

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.alex.challengeapp.data.local.entity.MovieEntity
import com.alex.challengeapp.data.remote.FakeMovieApi
import com.alex.challengeapp.data.remote.model.MovieDTO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalPagingApi
@RunWith(AndroidJUnit4::class)
class MovieMediatorTest {

    private val mockMovies = (0..100).map {
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

    private val mockApi = FakeMovieApi()

    private val mockDb = Room.inMemoryDatabaseBuilder(
        ApplicationProvider.getApplicationContext(),
        MovieDB::class.java,
    )
        .fallbackToDestructiveMigration()
        .build()

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
        mockDb.clearAllTables()
        mockApi.failureMessage = null
        mockApi.clearMovies()
    }

    @Test
    fun refreshLoadReturnsSuccessResultWhenMoreDataIsPresent() = runTest {
        mockMovies.forEach {
            mockApi.addMovies(it)
        }
        val remoteMediator = MovieMediator(mockApi, mockDb)

        val pagingState = PagingState<Int, MovieEntity>(
            listOf(),
            null,
            PagingConfig(20),
            20
        )

        val result = remoteMediator.load(
            LoadType.REFRESH, pagingState
        )

        assertTrue(result is RemoteMediator.MediatorResult.Success)
        assertFalse((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
    }
}