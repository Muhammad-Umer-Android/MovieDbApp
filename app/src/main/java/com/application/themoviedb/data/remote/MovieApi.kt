package com.application.themoviedb.data.remote
import com.application.themoviedb.data.remote.response.MovieListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @Author: Umer Dev
 * @Created: 03/07/2025
 * @File: MovieApi.kt
 */
interface MovieApi {
    @GET("movie/{category}")
    suspend fun getMoviesList(
        @Path("category") category: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): MovieListDto

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
        const val API_KEY = ""
    }
}