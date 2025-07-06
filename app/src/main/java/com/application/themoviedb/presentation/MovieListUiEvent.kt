package com.application.themoviedb.presentation

/**
 * @Author: Umer Dev
 * @Created: 03/07/2025
 * @File: MovieListUiEvent.kt
 */
sealed interface MovieListUiEvent {
    data class Paginate(val category: String) : MovieListUiEvent
    object Navigate : MovieListUiEvent
}