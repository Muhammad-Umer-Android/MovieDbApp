package com.application.themoviedb.screens.detail.presentation

import com.application.themoviedb.domain.model.Movie

/**
 * @Author: Umer Dev
 * @Created: 03/07/2025
 * @File: DetailsState.kt
 */
data class DetailsState(
    val isLoading: Boolean = false,
    val movie: Movie? = null
)