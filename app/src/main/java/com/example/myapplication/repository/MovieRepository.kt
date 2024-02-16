package com.example.myapplication.repository

/*created
 by
 Harsha Devnani*/

import com.example.myapplication.model.Movie
import com.example.myapplication.model.MovieDetails
import retrofit2.Response


interface MovieRepository {
    suspend fun getPopularMovies(page: Int):Response<Movie>
}
