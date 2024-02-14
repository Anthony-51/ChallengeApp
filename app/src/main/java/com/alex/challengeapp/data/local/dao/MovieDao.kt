package com.alex.challengeapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alex.challengeapp.data.local.entity.MovieEntity

@Dao
interface MovieDao {

      @Insert(onConflict = OnConflictStrategy.REPLACE)
      suspend fun insertAll(movies: List<MovieEntity>)

      @Query("SELECT * FROM movie")
      fun pagingSource(): PagingSource<Int, MovieEntity>

      @Query("DELETE FROM movie")
      suspend fun deleteAll()
}