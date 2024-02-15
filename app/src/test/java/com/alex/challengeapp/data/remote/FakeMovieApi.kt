package com.alex.challengeapp.data.remote

import com.alex.challengeapp.data.remote.model.MovieDTO
import com.alex.challengeapp.data.remote.model.MoviesResponse
import java.io.IOException

class FakeMovieApi(): MovieApi {
    private val mockResults = arrayListOf<MovieDTO>()

    var failureMessage: String? = null

    fun addMovies(movie: MovieDTO) {
        mockResults.add(movie)
    }

    fun clearMovies() {
        mockResults.clear()
    }
    override suspend fun getMovies(page: Int, apiKey: String): MoviesResponse {
        if (failureMessage != null) {
            throw IOException(failureMessage)
        }
        return MoviesResponse(page = page, results = mockResults, totalPages = mockResults.size / 20, totalResults = mockResults.size)
    }
}
