package com.application.themoviedb.di

import com.application.themoviedb.data.repository.MovieListRepositoryImpl
import com.application.themoviedb.domain.repository.MovieListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @Author: Umer Dev
 * @Created: 03/07/2025
 * @File: RepositoryModule.kt
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMovieListRepository(
        movieListRepositoryImpl: MovieListRepositoryImpl
    ): MovieListRepository

}