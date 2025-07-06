package com.application.themoviedb.domain.repository

import com.application.themoviedb.domain.model.Movie
import com.application.themoviedb.utils.Resource
import kotlinx.coroutines.flow.Flow

/**
 * @Author: Umer Dev
 * @Created: 03/07/2025
 * @File: MovieListRepository.kt
 */
interface MovieListRepository {
    suspend fun getMovieList(
        forceFetchFromRemote: Boolean,
        category: String,
        page: Int
    ): Flow<Resource<List<Movie>>>

    suspend fun getMovie(id: Int): Flow<Resource<Movie>>
}