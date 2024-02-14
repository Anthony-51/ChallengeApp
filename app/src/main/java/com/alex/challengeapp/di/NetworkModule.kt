package com.alex.challengeapp.di

import com.alex.challengeapp.data.remote.MovieApi
import com.alex.challengeapp.data.repository.MovieRepositoryImpl
import com.alex.challengeapp.data.repository.data_source.MovieDataSource
import com.alex.challengeapp.domain.repository.MovieRepository
import com.alex.challengeapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

      @Provides
      @Singleton
      fun provideRetrofit(): Retrofit {
            return Retrofit.Builder()
                  .baseUrl(Constants.BASE_URL)
                  .addConverterFactory(GsonConverterFactory.create())
                  .build()
      }

      @Provides
      @Singleton
      fun provideMovieApi(retrofit: Retrofit): MovieApi {
            return retrofit.create(MovieApi::class.java)
      }

      @Provides
      @Singleton
      fun provideMovieRepository(dataSource: MovieDataSource): MovieRepository = MovieRepositoryImpl(dataSource)

}