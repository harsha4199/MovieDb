package com.example.myapplication.repository

/*created
 by
 Harsha Devnani*/

import com.example.myapplication.api.ApiService
import com.example.myapplication.model.Movie
import com.example.myapplication.model.MovieDetails
import com.example.myapplication.utils.Constants
import retrofit2.Response
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val apiService: ApiService) : MovieRepository {
    override suspend fun getPopularMovies(page: Int): Response<Movie> {
        return apiService.getPopularMovies(Constants.API_KEY, page)
    }

}
