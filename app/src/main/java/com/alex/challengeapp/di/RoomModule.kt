package com.alex.challengeapp.di

import android.content.Context
import androidx.room.Room
import com.alex.challengeapp.data.local.MovieDB
import com.alex.challengeapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

      @Provides
      @Singleton
      fun provideMovieDB(
            @ApplicationContext context: Context
      ): MovieDB =
            Room.databaseBuilder(context, MovieDB::class.java, Constants.DB_NAME)
                  .build()

      @Provides
      @Singleton
      fun provideMovieDao(movieDB: MovieDB) = movieDB.movieDao()
}