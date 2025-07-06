package com.application.themoviedb.data.remote.response

/**
 * @Author: Umer Dev
 * @Created: 03/07/2025
 * @File: MovieListDto.kt
 */
data class MovieListDto(
    val page: Int,
    val results: List<MovieDto>,
    val total_pages: Int,
    val total_results: Int
)
