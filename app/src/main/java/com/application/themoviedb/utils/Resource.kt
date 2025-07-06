package com.application.themoviedb.utils

/**
 * @Author: Umer Dev
 * @Created: 03/07/2025
 * @File: Resource.kt
 */
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T>(val isLoading: Boolean = true) : Resource<T>(null)
}