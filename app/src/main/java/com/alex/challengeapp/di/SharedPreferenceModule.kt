package com.alex.challengeapp.di

import android.content.Context
import com.alex.challengeapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferenceModule {

      @Provides
      @Singleton
      fun provideSharedPreferences(@ApplicationContext context: Context) = context.getSharedPreferences(Constants.PREFERENCES_NAME, Context.MODE_PRIVATE)
}