package com.application.themoviedb.data.local.movie

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * @Author: Umer Dev
 * @Created: 03/07/2025
 * @File: MovieDatabase.kt
 */
@Database(
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase: RoomDatabase() {
    abstract val movieDao: MovieDao
}