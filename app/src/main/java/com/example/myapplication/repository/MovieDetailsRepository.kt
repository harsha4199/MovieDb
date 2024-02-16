package com.example.myapplication.repository

/*created
 by
 Harsha Devnani*/

import com.example.myapplication.model.MovieDetails
import retrofit2.Response

interface MovieDetailsRepository {
    suspend fun getMovieDetails(movieId: Int): Response<MovieDetails>
}