package com.alex.challengeapp.data.remote

import com.alex.challengeapp.data.remote.model.MoviesResponse as Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

      @GET("movie/upcoming")
      suspend fun getMovies(@Query("page") page: Int, @Query("api_key") apiKey: String): Response


}