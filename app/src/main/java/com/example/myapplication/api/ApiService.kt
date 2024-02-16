package com.example.myapplication.api

import com.example.myapplication.model.Movie
import com.example.myapplication.model.MovieDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

/*created
 by
 Harsha Devnani*/

interface ApiService {
    @GET("3/movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<Movie>


    @GET("3/movie/{movieId}")
    suspend fun getMovieDetails(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String,
    ): Response<MovieDetails>
}

