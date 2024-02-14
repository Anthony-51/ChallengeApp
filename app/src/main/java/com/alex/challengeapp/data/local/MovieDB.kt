package com.alex.challengeapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alex.challengeapp.data.local.dao.MovieDao
import com.alex.challengeapp.data.local.entity.MovieEntity

@Database(
      entities = [
            MovieEntity::class
      ],
      version = 1,
      exportSchema = false
)
abstract class MovieDB: RoomDatabase() {
      abstract fun movieDao(): MovieDao
}