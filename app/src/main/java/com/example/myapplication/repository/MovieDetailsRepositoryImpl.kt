package com.example.myapplication.repository

/*created
 by
 Harsha Devnani*/

import com.example.myapplication.api.ApiService
import com.example.myapplication.model.MovieDetails
import com.example.myapplication.utils.Constants
import retrofit2.Response
import javax.inject.Inject


class MovieDetailsRepositoryImpl @Inject constructor(private val apiService: ApiService) : MovieDetailsRepository{
    override suspend fun getMovieDetails(movieId: Int): Response<MovieDetails> {
        return apiService.getMovieDetails(movieId,Constants.API_KEY)
    }
}