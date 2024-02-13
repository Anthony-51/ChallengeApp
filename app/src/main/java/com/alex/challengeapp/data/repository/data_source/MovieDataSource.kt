package com.alex.challengeapp.data.repository.data_source

import com.alex.challengeapp.data.Response
import com.alex.challengeapp.data.remote.MovieApi
import com.alex.challengeapp.data.remote.model.ErrorResponse401
import com.alex.challengeapp.data.remote.model.ErrorResponse422
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
                  return if (e.code() == 422){
                        val response = Gson().fromJson(e.response()?.errorBody()?.charStream(), ErrorResponse422::class.java)
                        Response.Error(response.errors?.get(0) ?: "An unexpected error occured", e.code())
                  } else if (e.code() == 401){
                        val response = Gson().fromJson(e.response()?.errorBody()?.charStream(), ErrorResponse401::class.java)
                        Response.Error(response.message, e.code())
                  } else Response.Error(e.localizedMessage ?: "An unexpected error occured", e.code())
            } catch (e: IOException) {
                  Response.Error("No se pudo conectar con el servidor. Verifique su conexión",1)
            } catch (e: Exception) {
                  Response.Error(e.message ?: e.toString(), 1)
            }
      }
}