package com.alex.challengeapp.data.repository.data_source

import com.alex.challengeapp.data.Response
import com.alex.challengeapp.data.remote.MovieApi
import com.alex.challengeapp.data.remote.model.ErrorDTO
import com.alex.challengeapp.data.remote.model.MoviesResponse
import com.alex.challengeapp.util.Constants
import com.google.gson.Gson
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MovieDataSource @Inject constructor(
      private val service: MovieApi
){

      suspend fun getMoviesByPage(page: Int): Response<MoviesResponse> {
            return try {
                  val response = service.getMovies(page, Constants.API_KEY)
                  Response.Success(response)
            } catch (e: HttpException) {
                  val errorResponse = Gson().fromJson(e.response()?.errorBody()?.charStream(), ErrorDTO::class.java)
                  Response.Error(errorResponse.message?: errorResponse.errors?.get(0) ?: Constants.UNEXPECTED_ERROR, e.code())
            } catch (e: IOException) {
                  Response.Error(Constants.NETWORK_ERROR,1)
            } catch (e: Exception) {
                  Response.Error(e.message ?: Constants.UNEXPECTED_ERROR, 1)
            }
      }
}