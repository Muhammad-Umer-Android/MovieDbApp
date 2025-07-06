package com.application.themoviedb.presentation

import com.application.themoviedb.domain.model.Movie

/**
 * @Author: Umer Dev
 * @Created: 03/07/2025
 * @File: MovieListState.kt
 */
data class MovieListState(
    val isLoading: Boolean = false,

    val popularMovieListPage: Int = 1,
    val upcomingMovieListPage: Int = 1,

    val isCurrentPopularScreen: Boolean = true,

    val popularMovieList: List<Movie> = emptyList(),
    val upcomingMovieList: List<Movie> = emptyList()
)