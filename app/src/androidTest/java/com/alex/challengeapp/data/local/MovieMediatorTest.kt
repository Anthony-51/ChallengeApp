package com.alex.challengeapp.data.local

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.alex.challengeapp.data.local.entity.MovieEntity
import com.alex.challengeapp.data.remote.FakeMovieApi
import com.alex.challengeapp.data.remote.model.MovieDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
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
        InstrumentationRegistry.getInstrumentation().targetContext,
        MovieDB::class.java,
    )
        .fallbackToDestructiveMigration()
        .build()

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.IO)
    }

    @After
    fun tearDown() {
        mockDb.clearAllTables()
        mockApi.failureMessage = null
        mockApi.clearMovies()
        Dispatchers.resetMain()
    }

    /**
     * Validar que mockApi retorne datos validos, validar que load() muestre MediatorResult.Success y que no sea el final de la paginacion (endOfPaginationReached = false)
     * */
    @Test
    fun validateIfLoadReturnsSuccessAndNotEndOfPagination() = runTest {
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

    /**
     * Validar que mockApi retorne datos validos, validar que load() muestre MediatorResult.Success y que sea el final de la paginacion (endOfPaginationReached = true)
     */
    @Test
    fun validateIfLoadReturnsSuccessAndEndOfPagination() = runTest {
        val remoteMediator = MovieMediator(
            mockApi,
            mockDb
        )

        val pagingState = PagingState<Int, MovieEntity>(
            listOf(),
            null,
            PagingConfig(20),
            20
        )

        val result = remoteMediator.load(LoadType.REFRESH, pagingState)
        assertTrue( result is RemoteMediator.MediatorResult.Success )
        assertTrue( (result as RemoteMediator.MediatorResult.Success).endOfPaginationReached )
    }

    /**
     * Validar que mockApi retorne error y validar que load() muestre MediatorResult.Error
     */
    @Test
    fun validateIfLoadReturnsErrorWhenApiThrows() = runTest {
        mockApi.failureMessage = "Throw test failure"
        val remoteMediator = MovieMediator(
            mockApi,
            mockDb
        )

        val pagingState = PagingState<Int, MovieEntity>(
            listOf(),
            null,
            PagingConfig(20),
            20
        )

        val result = remoteMediator.load(LoadType.REFRESH, pagingState)
        assertTrue(result is RemoteMediator.MediatorResult.Error )
    }
}