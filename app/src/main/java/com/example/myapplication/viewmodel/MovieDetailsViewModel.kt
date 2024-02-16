package com.example.myapplication.viewmodel

/*created
 by
 Harsha Devnani*/

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.MovieDetails
import com.example.myapplication.repository.MovieDetailsRepository
import com.example.myapplication.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val repository: MovieDetailsRepository) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()


    /** This function is responsible for fetching movie details
     from the repository ,It takes a movie ID as input and
    returns a Flow of MovieDetails **/

    fun getMovieDetails(movieId: Int): Flow<MovieDetails> = flow {
        _isLoading.value = true
        repository.getMovieDetails(movieId).body()?.let {
            emit(it)
        _isLoading.value=false
        }
    }.catch {
        _isLoading.value = false


    }
}
