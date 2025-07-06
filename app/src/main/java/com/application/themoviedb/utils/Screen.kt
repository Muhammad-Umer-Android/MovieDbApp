package com.application.themoviedb.utils

/**
 * @Author: Umer Dev
 * @Created: 03/07/2025
 * @File: Screen.kt
 */
sealed class Screen(val rout: String) {
    object Home : Screen("main")
    object PopularMovieList : Screen("popularMovie")
    object UpcomingMovieList : Screen("upcomingMovie")
    object Details : Screen("details")
}